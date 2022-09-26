package com.greatLearning.studentManagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatLearning.studentManagement.entity.Student;

@Repository
public class StudentServiceImpl implements StudentService {

	// create session
	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	StudentServiceImpl(SessionFactory sessionFactory) {
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}

	
	@Transactional
	public List<Student> findAll() {

		// find all the records from the database table
		List<Student> students = session.createQuery("from Student").list();

		return students;
	}

	
	@Transactional
	public Student findById(int id) {

		Student student = new Student();

		// find record with Id from the database table
		student = session.get(Student.class, id);

		return student;
	}

	
	@Transactional
	public void save(Student theStudent) {

		// save transaction
		session.saveOrUpdate(theStudent);

	}

	
	@Transactional
	public void deleteById(int id) {

		// get transaction
		Student student = session.get(Student.class, id);

		// delete record
		session.delete(student);

	}

}