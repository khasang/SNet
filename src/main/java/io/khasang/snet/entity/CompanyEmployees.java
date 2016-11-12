package io.khasang.snet.entity;


import javax.persistence.*;

@Entity
@Table(name = "comp_empl")
public class CompanyEmployees {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @EmbeddedId
    CompanyEmployeesID pk;


    @Column (name = "comp_dep")
    private String department;

    public CompanyEmployees(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
