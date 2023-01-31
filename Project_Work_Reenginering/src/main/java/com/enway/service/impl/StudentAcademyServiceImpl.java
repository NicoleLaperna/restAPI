package com.enway.service.impl;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.enway.entity.Academy;
import com.enway.entity.Student;
import com.enway.entity.Translate;
import com.enway.repository.AcademyRepository;
import com.enway.repository.StudentRepository;
import com.enway.service.StudentAcademyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentAcademyServiceImpl implements StudentAcademyService {

	@Autowired
	private AcademyRepository academyRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Academy addOrUpdateAcademy(Academy academy) {
		Academy academySaved = academyRepository.save(academy);

		List<Student> students = academy.getStudents();

		if (students != null) {
			for (Student student : students) {
				studentRepository.save(student);
			}
		}
		return academySaved;
	}

	@Override
	public Student addOrUpdateStudent(Student student) {

		return studentRepository.save(student);
	}

	@Override
	public List<Academy> findAllAcademies() {

		return academyRepository.findAll();

	}

	@Override
	public Map<String, Boolean> removeAcademyAndStudents(String code) {

		Map<String, Boolean> operation = new HashMap<>();

		if (academyRepository.existsById(code)) {

			academyRepository.deleteById(code);

			if (!academyRepository.existsById(code)) {

				operation.put("deletion", true);
			} else {

				operation.put("deletion", false);
				;
			}

		} else {

			operation.put("deletion", false);
			;
		}

		return operation;
	}

	@Override
	public Map<String, Boolean> removeStudent(String passportNumber) {

		Map<String, Boolean> operation = new HashMap<>();

		if (studentRepository.existsById(passportNumber)) {

			studentRepository.deleteById(passportNumber);

			if (!studentRepository.existsById(passportNumber)) {

				operation.put("deletion", true);
			} else {

				operation.put("deletion", false);
				;
			}

		} else {

			operation.put("deletion", false);
			;
		}

		return operation;
	}

	@Override
	public Student findStudentById(String passportNumber) {
		Student studentFound=null;
		try {
			studentFound = studentRepository.findById(passportNumber).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentFound;

	}

	@Override
	public Academy findAcademyById(String code) {

		return academyRepository.findById(code).get();

	}

	public List<Student> findStudentsByAcademy(String codeAcademy) {

		Academy academy = findAcademyById(codeAcademy);

		return studentRepository.findByAcademy(academy);
	}

	public String parseStudentXmlFromObject(Student student) throws JAXBException{
		JAXBContext context= JAXBContext.newInstance(Student.class);
		Marshaller mar = context.createMarshaller();
		mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		mar.marshal(student, sw);
		String result = sw.toString();
		return result;
		
	}
	//Json
	public String parseStudentJson(Student student) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String studentParsed = mapper.writeValueAsString(student);
			return studentParsed;
		} catch (Exception e) {
			System.out.println("Something went wrong");
		}
		return null;
	}
	
	//Rest API
	public String invokeGetApi() {
		RestTemplate restTemplate= new RestTemplate();
		String url = "https://www.7timer.info/bin/astro.php?lon=113.2&lat=23.1&ac=0&unit=metric&output=json&tzshift=0";
		String result = restTemplate.getForObject(url, String.class);
		return result;
	}
	
	public void invokePostApi(Translate translate) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://google-translate1.p.rapidapi.com/language/translate/v2";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept-Encoding", "application/gzip");
		headers.set("X-RapidAPI-Key", "adaa531134msh51085e7b56d35d2p1c1bd5jsn6ba9ac480eac");
		headers.set("X-RapidAPI-Host", "google-translate1.p.rapidapi.com");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		Map<String, String> map = new HashMap<>();
		map.put("q", translate.q);
		map.put("target", translate.target);
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
		if (response.getStatusCode() == HttpStatus.CREATED) {
		    System.out.println("Request Successful");
		    System.out.println(response.getBody());
		} else {
		    System.out.println("Request Failed");
		    System.out.println(response.getStatusCode());
		}

		
	}
}
