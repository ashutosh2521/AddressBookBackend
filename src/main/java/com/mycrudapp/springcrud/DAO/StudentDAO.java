package com.mycrudapp.springcrud.DAO;

import com.mycrudapp.springcrud.Entity.Student;

import java.util.List;

public interface StudentDAO
{
    void save(Student student);
    Student read(int id);

    List<Student> getAllStudents();

    List<Student> getStudentsByLastName(String lastName);

    void updateStudent(int id);

    void removeStudent(int id);

    int removeAllStudents();
}
