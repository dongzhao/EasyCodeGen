package com.dzhao.common.codegen.domain;

import com.dzhao.common.codegen.impl.AbstractGenerator;
import com.dzhao.common.codegen.impl.DaoGenerator;
import com.dzhao.common.codegen.impl.RepositoryGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum GeneratorType {
    DAO("com.dzhao.common.codegen.annotation.GenerateDao", DaoGenerator.class),
    REPOSITORY("com.dzhao.common.codegen.annotation.GenerateRepository", RepositoryGenerator.class);
    private final String annotationClassName;
    private final Class<? extends AbstractGenerator> clazz;

    private GeneratorType(String annotationClassName, Class<? extends AbstractGenerator> clazz){
        this.annotationClassName = annotationClassName;
        this.clazz = clazz;
    }

    public String getAnnotationClassName(){
        return annotationClassName;
    }

    public Class<? extends AbstractGenerator> getClazz(){
        return clazz;
    }

    public static List<String> listAnnotationClassNames(){
        List<String> values = new ArrayList<String>();
        for(GeneratorType type : Arrays.asList(GeneratorType.values())) {
            values.add(type.getAnnotationClassName());
        }
        return values;
    }

    public static GeneratorType valueFrom(String annotationClassName){
        for(GeneratorType type : Arrays.asList(GeneratorType.values())) {
            if(annotationClassName.equals(type.getAnnotationClassName())){
                return type;
            }
        }
        throw new UnsupportedOperationException("unsupported enum type of the annotation class name ["+ annotationClassName +"]");
    }
}
