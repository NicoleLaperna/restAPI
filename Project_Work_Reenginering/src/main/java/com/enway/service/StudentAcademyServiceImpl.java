package com.enway.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enway.entity.Academy;
import com.enway.entity.Student;
import com.enway.repository.AcademyRepository;
import com.enway.repository.StudentRepository;

@Service
public class StudentAcademyServiceImpl implements StudentAcademyService {

	@Autowired
	private AcademyRepository academyRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Academy  addOrUpdateAcademy(Academy academy) {
		Academy academySaved = academyRepository.save(academy);
		
		List<Student> students = academy.getStudents();
		
		if(students != null) {
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
	public Map<String,Boolean> removeAcademyAndStudents(String code) {
		
		Map<String,Boolean> operation = new HashMap<>();

		if (academyRepository.existsById(code)) {
			
			academyRepository.deleteById(code);

			if (!academyRepository.existsById(code)) {

				operation.put("deletion", true);
			} else {
				
				operation.put("deletion", false);;
			}
			
		} else {
			
			operation.put("deletion", false);;
		}
		
		return operation;
	}

	@Override
	public Map<String,Boolean> removeStudent(String passportNumber) {
		
		Map<String,Boolean> operation = new HashMap<>();

		if (studentRepository.existsById(passportNumber)) {
			
			studentRepository.deleteById(passportNumber);

			if (!studentRepository.existsById(passportNumber)) {

				operation.put("deletion", true);
			} else {
				
				operation.put("deletion", false);;
			}
			
		} else {
			
			operation.put("deletion", false);;
		}
		
		return operation;
	}

	@Override
	public Student findStudentById(String passportNumber) {

		return studentRepository.findById(passportNumber).get();

	}

	@Override
	public Academy findAcademyById(String code) {

		return academyRepository.findById(code).get();

	}
	
	public List<Student> findStudentsByAcademy(String codeAcademy) {

		Academy academy = findAcademyById(codeAcademy);

		return studentRepository.findByAcademy(academy);
	}

}
