package com.dzhao.common.database.domain;


import com.dzhao.common.codegen.annotation.GenerateDao;
import com.dzhao.common.codegen.annotation.GenerateRepository;

@GenerateDao(
        packageName = "com.dzhao.common.database.dao",
        simpleName = "CustomerDao")
@GenerateRepository(
        modelPackageName = "com.dzhao.common.database.domain.*",
        repositoryPackageName = "com.dzhao.common.database.repositories",
        modelSimpleName = "Customer",
        simpleName = "CustomerRepository")
public class Customer {
    private long id;
    private String firstName;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
