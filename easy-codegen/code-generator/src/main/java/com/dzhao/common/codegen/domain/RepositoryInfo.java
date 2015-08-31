package com.dzhao.common.codegen.domain;

public class RepositoryInfo {
    private String modelPackageName;
    private String repositoryPackageName;
    private String modelSimpleName;
    private String SimpleName;
    private String targetPath;

    public String getModelPackageName() {
        return modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    public String getRepositoryPackageName() {
        return repositoryPackageName;
    }

    public void setRepositoryPackageName(String repositoryPackageName) {
        this.repositoryPackageName = repositoryPackageName;
    }

    public String getModelSimpleName() {
        return modelSimpleName;
    }

    public void setModelSimpleName(String modelSimpleName) {
        this.modelSimpleName = modelSimpleName;
    }

    public String getSimpleName() {
        return SimpleName;
    }

    public void setSimpleName(String simpleName) {
        SimpleName = simpleName;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
