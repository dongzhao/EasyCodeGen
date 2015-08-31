package com.dzhao.common.codegen.impl;


import com.dzhao.common.codegen.annotation.GenerateDao;
import com.dzhao.common.codegen.annotation.GenerateRepository;
import com.dzhao.common.codegen.domain.DaoInfo;
import com.dzhao.common.codegen.domain.RepositoryInfo;
import com.dzhao.common.codegen.plugins.freemarker.FreeMarkerWriter;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.util.Collection;

public class RepositoryGenerator extends AbstractGenerator{

    public RepositoryGenerator(
            TypeElement typeElement,
            RoundEnvironment roundEnv,
            ProcessingEnvironment processingEnv){
        super(typeElement, roundEnv, processingEnv);
    }

    @Override
    public void execute() {
        Collection<? extends Element> types = roundEnv.getElementsAnnotatedWith(typeElement);
        for (Element element : types){
            StringBuilder targetSourcePath = new StringBuilder();
            GenerateRepository repository = element.getAnnotation(GenerateRepository.class);
            RepositoryInfo info = new RepositoryInfo();
            info.setModelPackageName(repository.modelPackageName());
            info.setRepositoryPackageName(repository.repositoryPackageName());
            info.setModelSimpleName(repository.modelSimpleName());
            info.setSimpleName(repository.simpleName());

            String modelSimpleName = repository.modelSimpleName();
            String repositoryPackageName = repository.repositoryPackageName();
            String modelPackageName = repository.modelPackageName();
            String simpleName = repository.simpleName();
            try {
                // to add the target folder path
                String sourcePath = processingEnv.getFiler().getResource(StandardLocation.SOURCE_OUTPUT, "", "test").toUri().getPath();
                targetSourcePath.append(sourcePath.substring(0, sourcePath.lastIndexOf("/target/")) + "/");
                System.out.println("targetSourcePath: " + targetSourcePath.toString());
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            // to aggregate the target path from annotation target
            targetSourcePath.append(repository.target());
            System.out.println("targetSourcePath: " + targetSourcePath.toString());
            // to aggregate the target path from annotation package name
            System.out.println("modelPackageName: " + modelPackageName);
            System.out.println("repsitoryPackageName: " + repositoryPackageName);
            System.out.println("repsitoryPackageName: " + repositoryPackageName.replace(".", "/"));
            targetSourcePath.append(repositoryPackageName.replace(".", "/") + "/");
            System.out.println("targetSourcePath: " + targetSourcePath.toString());
            // to aggregate the target path from annotation simple dao class name
            targetSourcePath.append(simpleName + ".java");
            System.out.println("targetSourcePath: " + targetSourcePath.toString());
            FreeMarkerWriter writer = new FreeMarkerWriter("src/main/resources/repository.ftl", targetSourcePath.toString(), info);
            writer.write();
        }
    }
}
