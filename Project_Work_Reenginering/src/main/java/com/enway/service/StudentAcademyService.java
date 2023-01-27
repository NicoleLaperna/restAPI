package com.enway.service;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import com.enway.entity.Academy;
import com.enway.entity.Student;

public interface StudentAcademyService {

	public Academy addOrUpdateAcademy(Academy academy);

	public Student addOrUpdateStudent(Student student);

	public List<Academy> findAllAcademies();

	public Map<String,Boolean> removeAcademyAndStudents(String code);

	public Map<String,Boolean> removeStudent(String passportNumber);

	public Student findStudentById(String passportNumber);

	public Academy findAcademyById(String code);
	
	public List<Student> findStudentsByAcademy(String codeAcademy);
	
	public String parseStudentXmlFromObject(Student student) throws JAXBException;
	
	public String parseStudentJson(Student student);
	
	public String invokeGetApi();
	
}
