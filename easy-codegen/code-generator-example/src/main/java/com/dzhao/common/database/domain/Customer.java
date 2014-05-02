package com.dzhao.common.database.domain;


import com.dzhao.common.codegen.annotation.GenerateDao;

@GenerateDao(packageName = "com.dzhao.common.database.dao", simpleName = "CustomerDao")
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
