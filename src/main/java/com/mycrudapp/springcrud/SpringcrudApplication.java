package com.mycrudapp.springcrud;

import com.mycrudapp.springcrud.DAO.StudentDAO;
import com.mycrudapp.springcrud.Entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.*;

@SpringBootApplication
public class SpringcrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcrudApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO)
	{
		return runner -> createStudent(studentDAO);
		//return runner -> readStudent(studentDAO);
		//return  runner -> printStudentsByLastName(studentDAO);
		//return runner -> updateStudent(studentDAO);
		//return runner -> removeStudent(studentDAO);
		//return runner -> removeAllStudents(studentDAO);
	}

	private void removeAllStudents(StudentDAO studentDAO)
	{
		System.out.println("Deleted num of rows = " + studentDAO.removeAllStudents());
	}

	private void removeStudent(StudentDAO studentDAO)
	{
		System.out.println("Before Deleting:");
		printAllStudent(studentDAO);
		studentDAO.removeStudent(1);
		System.out.println("After Deleting:");
		printAllStudent(studentDAO);
	}

	private void updateStudent(StudentDAO studentDAO)
	{
		studentDAO.updateStudent(1);
		printAllStudent(studentDAO);
	}

	private void printStudentsByLastName(StudentDAO studentDAO)
	{
		List<Student> students = studentDAO.getStudentsByLastName("Kumar");
		for(Student st: students)
		{
			System.out.println(st);
		}
	}
	private void printAllStudent(StudentDAO studentDAO)
	{
		List<Student> students = studentDAO.getAllStudents();
		for(Student st: students)
		{
			System.out.println(st);
		}
	}

	private void readStudent(StudentDAO studentDAO)
	{
		Student student = studentDAO.read(1);
		System.out.println(student);
	}

	private void createStudent(StudentDAO studentDAO)
	{
		Student student = new Student("Ajit", "Anand", "ajit.a@adp.com");
		studentDAO.save(student);
		System.out.println("Id of ravi is:" + student.getId());
	}
}
