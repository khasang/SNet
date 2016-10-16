package io.khasang.snet.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    private int id;
    private String name;
    private int age;
    private String city;
    private double salary;

    public Employee(int id, String name, int age, String city, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format(
                "Employee[id=%d, Name='%s', age='%s', city='%s', salary='%s'] \r",
                id, name, age, city, salary);
    }
}
