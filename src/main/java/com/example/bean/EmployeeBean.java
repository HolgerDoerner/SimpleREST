package com.example.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String company;
    private String department;
    private String employerId;
    private String email;

    public EmployeeBean() {
        this.setId(-1);
        this.setFirstName("");
        this.setLastName("");
        this.setBirthDate(LocalDate.of(1000, 01, 01));
        this.setGender("");
        this.setCompany("");
        this.setDepartment("");
        this.setEmployerId("");
        this.setEmail("");
    }

    public EmployeeBean(Integer id,
                        String firstName,
                        String lastName,
                        LocalDate birthDate,
                        String gender,
                        String company,
                        String department,
                        String employerId,
                        String email) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthDate(birthDate);
        this.setGender(gender);
        this.setCompany(company);
        this.setDepartment(department);
        this.setEmployerId(employerId);
        this.setEmail(email);
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (Objects.isNull(obj)) {
            return false;
        }

        if (!(obj instanceof EmployeeBean)) {
            return false;
        }

        EmployeeBean other = (EmployeeBean) obj;

        if (!Objects.equals(other.getId(), this.id)) {
            return false;
        }

        return true;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}