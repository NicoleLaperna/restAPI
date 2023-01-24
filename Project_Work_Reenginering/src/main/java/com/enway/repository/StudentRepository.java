package com.enway.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.enway.entity.Academy;
import com.enway.entity.Student;


public interface StudentRepository extends JpaRepository<Student, String> {
	
	public List<Student> findByAcademy(Academy academy);
}
