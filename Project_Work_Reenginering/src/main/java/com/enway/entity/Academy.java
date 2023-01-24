package com.enway.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Academy {

	@Id
	private String code;

	private String title;

	private String location;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "academy",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE,orphanRemoval = true)
	private List<Student> students = new ArrayList<>();

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Academy() {

	}

	public Academy(String code, String title, String location, String startDate, String endDate) {

		this.code = code;
		this.title = title;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
