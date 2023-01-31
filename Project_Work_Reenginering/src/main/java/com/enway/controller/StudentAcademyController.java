package com.enway.controller;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.enway.entity.Academy;
import com.enway.entity.Joke;
import com.enway.entity.Student;
import com.enway.entity.Translate;
import com.enway.service.StudentAcademyService;

@RestController
@RequestMapping("/academies-service")
public class StudentAcademyController {

	@Autowired
	private StudentAcademyService studentAcademyService;

	//Visualizzazione List Academies
	@GetMapping("/academies")
	public List<Academy> findAllAcademies() {

		return studentAcademyService.findAllAcademies();
	}

	//Inserimento Academy
	@PostMapping("/academies")
	public Academy addAcademy(@RequestBody Academy academy) {

		 return studentAcademyService.addOrUpdateAcademy(academy);
		 
	}
	
	@PutMapping("/academies")
	public Academy updateAcademy(@RequestBody Academy academy) {

		return studentAcademyService.addOrUpdateAcademy(academy);
	}

	//Rimozione Academy
	@DeleteMapping("/academies/{code}")
	public Map<String,Boolean> removeAcademyAndStudents(@PathVariable String code) {

		return studentAcademyService.removeAcademyAndStudents(code);
	}


	//Visualizzazione Studenti Per Academy 
	@GetMapping("/students/code/{code}")
	public List<Student> findStudentsByAcademy(@PathVariable String code) {

		return studentAcademyService.findStudentsByAcademy(code);
	}


	
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {

		return studentAcademyService.addOrUpdateStudent(student);
	}
	
	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student student) {
		return studentAcademyService.addOrUpdateStudent(student);
	}

	//Remove 
	@DeleteMapping("/students/{passportNumber}")
	public Map<String,Boolean> removeStudent(@PathVariable String passportNumber) {			
		return studentAcademyService.removeStudent(passportNumber);
	}
	
	
	@PostMapping("/student/xml/{passportNumber}")
	public String getStudentStringFromXml(@RequestBody Student student) throws JAXBException {
		return studentAcademyService.parseStudentXmlFromObject(student);
	}
	
	@PostMapping("/student/json/{passportNumber}")
	public String getStudentStringFromJson(@RequestBody Student student) throws JAXBException {
		return studentAcademyService.parseStudentJson(student);
	}
	
	@GetMapping("/student-found")
	public ResponseEntity<Student> findStudentById(@RequestParam(name="id") String passportNumber) {
		if(passportNumber==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Student student = studentAcademyService.findStudentById(passportNumber);
		if(student==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(student, HttpStatus.OK);
		}
	}
	@GetMapping("/api-get")
	public String getApi() {
		return studentAcademyService.invokeGetApi();
	}
	
	@PostMapping("/api-post")
	public void postApi(@RequestBody Joke joke) {
		studentAcademyService.invokePostApi(joke);
	}
}
