package io.khasang.snet.Entity;

import javax.persistence.*;

@Entity
@Table (name = "Company")
public class Company {
    @Id
    @Column (name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "name")
    private String name;

    @Column (name = "age")
    private int age;

    @Column (name = "address")
    private String address;

    @Column (name = "salary")
    private double salary;

    public Company(int id, String name, int age, String address, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Company(){

    }
}
