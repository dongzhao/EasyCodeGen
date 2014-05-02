package com.dzhao.common.codegen.processor;

import com.dzhao.common.codegen.domain.GeneratorType;
import com.dzhao.common.codegen.impl.AbstractGenerator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CodeGenProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes(){
        return new HashSet<String>(GeneratorType.listAnnotationClassNames());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        System.out.println("#####################################################");
        System.out.println("start generating code ...");
        for(TypeElement typeElement : annotations){
            GeneratorType type = getTypeByName(typeElement.getQualifiedName().toString());
            if(type==null){
                continue;
            }
            Class<? extends AbstractGenerator> cls = type.getClazz();

            try {
                Constructor<? extends AbstractGenerator> ct = cls.getConstructor(
                        TypeElement.class,
                        RoundEnvironment.class,
                        ProcessingEnvironment.class
                );

                try {
                    AbstractGenerator generator = ct.newInstance(typeElement, roundEnv, processingEnv);
                    generator.execute();
                } catch (InstantiationException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IllegalAccessException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (InvocationTargetException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            } catch (NoSuchMethodException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return false;
    }

    private GeneratorType getTypeByName(String typeName){
        try{
            return GeneratorType.valueFrom(typeName);
        }catch(Exception e){
            return null;
        }
    }

/*    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("#####################################################");
        System.out.println("start generating code ...");
        for(TypeElement typeElement : annotations){
            System.out.println("TypeElement: " + typeElement.getQualifiedName().toString());
            System.out.println("GenerateDao: " + GenerateDao.class.getName());
            if(typeElement.getQualifiedName().toString().equals(GenerateDao.class.getName())){
                System.out.println("continue process ... ");
                Collection<? extends Element> types = roundEnv.getElementsAnnotatedWith(typeElement);
                //roundEnvironment.getElementsAnnotatedWith(GenerateRules.class)
                for (Element element : types){
                    //for (Element element : roundEnv.getElementsAnnotatedWith(GenerateDao.class)){
*//*                    Symbol.ClassSymbol clazz = (Symbol.ClassSymbol) element;
                    System.out.println("Element simple name: " + clazz.getSimpleName());
                    System.out.println("Element quliried name: " + clazz.getQualifiedName());*//*
                    GenerateDao dao = element.getAnnotation(GenerateDao.class);
                    String simpleName = dao.simpleName();
                    String packageName = dao.packageName();
                    String targetPath = dao.target();
                    System.out.println("simpleName: " + simpleName);
                    System.out.println("packageName: " + packageName);
                    System.out.println("targetPath: " + targetPath);


                    Configuration cfg = new Configuration();
                    Template template = null;
                    try {
                        template = cfg.getTemplate("src/main/resources/dao.ftl");
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    String sourcePath = null;
                    try {
                        sourcePath = processingEnv.getFiler().getResource(StandardLocation.SOURCE_OUTPUT, "", "test").toUri().getPath();
                        sourcePath = (sourcePath.substring(0, sourcePath.lastIndexOf("/target/")) + "/");
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }

                    String outputFilePath = sourcePath + targetPath + packageName.replace(".", "/") + "/" + simpleName + ".java";

                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("daoPackage", packageName);
                    data.put("daoSimpleName", simpleName);

                    FileWriter writer = null;
                    File file = new File(outputFilePath);
                    file.getParentFile().mkdirs();
                    try {
                        file.createNewFile();
                        writer = new FileWriter(file);
                        if (template != null) {
                            try {
                                template.process(data, writer);
                            } catch (TemplateException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }
        }


        return false;
    }*/
}
