package com.dzhao.common.codegen.impl;


import com.dzhao.common.codegen.annotation.GenerateDao;
import com.dzhao.common.codegen.domain.DaoInfo;
import com.dzhao.common.codegen.plugins.freemarker.FreeMarkerWriter;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.Element;
import javax.tools.StandardLocation;

import java.io.IOException;
import java.util.Collection;

public class DaoGenerator extends AbstractGenerator{

    public DaoGenerator(
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
            GenerateDao dao = element.getAnnotation(GenerateDao.class);

            DaoInfo info = new DaoInfo();
            info.setPackageName(dao.packageName());
            info.setSimpleName(dao.simpleName());

            String simpleName = dao.simpleName();
            String packageName = dao.packageName();
            try {
                // to add the target folder path
                String sourcePath = processingEnv.getFiler().getResource(StandardLocation.SOURCE_OUTPUT, "", "test").toUri().getPath();
                targetSourcePath.append(sourcePath.substring(0, sourcePath.lastIndexOf("/target/")) + "/");
                System.out.println("targetSourcePath: " + targetSourcePath.toString());
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            // to aggregate the target path from annotation target
            targetSourcePath.append(dao.target());
            System.out.println("targetSourcePath: " + targetSourcePath.toString());
            // to aggregate the target path from annotation package name
            System.out.println("packageName: " + packageName);
            System.out.println("packageName: " + packageName.replace(".", "/"));
            targetSourcePath.append(packageName.replace(".", "/") + "/");
            System.out.println("targetSourcePath: " + targetSourcePath.toString());
            // to aggregate the target path from annotation simple dao class name
            targetSourcePath.append(simpleName + ".java");
            System.out.println("targetSourcePath: " + targetSourcePath.toString());

            FreeMarkerWriter writer = new FreeMarkerWriter("src/main/resources/dao.ftl", targetSourcePath.toString(), info);
            writer.write();
        }
    }
}
