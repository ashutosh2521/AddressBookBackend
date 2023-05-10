package com.mycrudapp.springcrud.DAO;

import com.mycrudapp.springcrud.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;
    @Autowired
    public StudentDAOImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student)
    {
        entityManager.persist(student);
    }

    @Override
    public Student read(int id) {
        return  entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = entityManager.createQuery("From Student order by lastName desc", Student.class);
        List<Student> students = query.getResultList();
        return students;
    }

    @Override
    public List<Student> getStudentsByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery("From Student where lastName=:lname", Student.class);
        query.setParameter("lname", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateStudent(int id)
    {
        Student student = entityManager.find(Student.class, 1);
        student.setLastName("Sharma");
        student.setEmail("ashutosh.s@google.com");
        entityManager.merge(student);
        //Updated student
    }

    @Override
    @Transactional
    public void removeStudent(int id)
    {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public int removeAllStudents()
    {
        int numberOfRowsUpdated = entityManager.createQuery("Delete From Student").executeUpdate();
        return numberOfRowsUpdated;
    }

}
