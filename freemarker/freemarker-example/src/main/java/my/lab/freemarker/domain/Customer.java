package my.lab.freemarker.domain;

import my.lab.freemarker.GenerateDao;

@GenerateDao(packageName = "my.lab.freemarker.dao", simpleName = "CustomerDao")
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
