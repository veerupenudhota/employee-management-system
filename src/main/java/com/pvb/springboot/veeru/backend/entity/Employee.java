package com.pvb.springboot.veeru.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Employee {

    
    private Long id;  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;  

    private String firstName;
    private String middleName;
    private String lastName;
    private String loginId;

    @Column(name = "date_of_birth")
    @JsonProperty("dob")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String department;
    private Double salary;

    @Column(name = "permanent_address", nullable = false)
    private String permanentAddress;

    @Column(name = "current_address", nullable = false)
    private String currentAddress;

    private String idProofFilePath;
    private String idProofFilename;
    private int idProofSize;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	public String getIdProofFilePath() {
		return idProofFilePath;
	}
	public void setIdProofFilePath(String idProofFilePath) {
		this.idProofFilePath = idProofFilePath;
	}
	public String getIdProofFilename() {
		return idProofFilename;
	}
	public void setIdProofFilename(String idProofFilename) {
		this.idProofFilename = idProofFilename;
	}
	public int getIdProofSize() {
		return idProofSize;
	}
	public void setIdProofSize(int idProofSize) {
		this.idProofSize = idProofSize;
	}
	public void setIdProofPath(String string) {
		
	}

    
}
