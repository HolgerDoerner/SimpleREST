package com.example.bean;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public EmployeeBean(Integer id, String firstName, String lastName, LocalDate birthDate, String gender,
            String company, String department, String employerId, String email) {
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

    public static EmployeeBean ofSingleResult(ResultSet res) throws SQLException {
        if (res.next()) {
            return new EmployeeBean(res.getInt(1),
                                    res.getString(2),
                                    res.getString(3),
                                    LocalDate.parse(res.getString(4)),
                                    res.getString(5),
                                    res.getString(6),
                                    res.getString(7),
                                    res.getString(8),
                                    res.getString(9));
        }

        return new EmployeeBean();
    }

    public static List<EmployeeBean> ofResultSet(ResultSet res) throws SQLException {
        List<EmployeeBean> list = new ArrayList<>();

        while (res.next()) {
            list.add(new EmployeeBean(res.getInt(1),
                                    res.getString(2),
                                    res.getString(3),
                                    LocalDate.parse(res.getString(4)),
                                    res.getString(5),
                                    res.getString(6),
                                    res.getString(7),
                                    res.getString(8),
                                    res.getString(9)));
        }

        return list;
    }

    public EmployeeBean update(EmployeeBean other) {
        if (!other.getFirstName().isEmpty()) {
            this.setFirstName(other.getFirstName());
        }

        if (!other.getLastName().isEmpty()) {
            this.setLastName(other.getLastName());
        }

        if (!Objects.equals(this.getBirthDate(), other.getBirthDate())) {
            this.setBirthDate(other.getBirthDate());
        }

        if (!other.getGender().isEmpty()) {
            this.setGender(other.getGender());
        }

        if (!other.getCompany().isEmpty()) {
            this.setCompany(other.getCompany());
        }

        if (!other.getDepartment().isEmpty()) {
            this.setDepartment(other.getDepartment());
        }

        if (!other.getEmployerId().isEmpty()) {
            this.setEmployerId(other.getEmployerId());
        }

        if (!other.getEmail().isEmpty()) {
            this.setEmail(other.getEmail());
        }

        return this;
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