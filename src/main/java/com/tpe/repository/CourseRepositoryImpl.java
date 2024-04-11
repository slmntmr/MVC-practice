package com.tpe.repository;

import com.tpe.domain.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Course course) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(course);

        transaction.commit();
        session.close();

    }

    @Override
    public List<Course> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<Course> courses = session.createQuery("FROM Course", Course.class).getResultList();

        tx.commit();
        session.close();

        return courses;

    }

    @Override
    public Optional<Course> findById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Course course = session.get(Course.class, id);

        // course objemin null olma ihtimaline karşı, kendisini bir optional içerisine ofNullable ile gönderdik.
        Optional<Course> optional = Optional.ofNullable(course);

        tx.commit();
        session.close();

        return optional;

    }

    @Override
    public void delete(Long id) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        // Load ile, database'e gitmeden, belirtilen ID ile bir Course objesi oluşturuldu. Ve bu id ile belirtilen data, database'den silindi.
        Course course = session.load(Course.class, id);

        // Get ile, database'e giderek, belirtilen ID ile bir Course datası getirildi.
        //Course course1 = session.get(Course.class, id);

        session.delete(course);

        tx.commit();
        session.close();

    }
}
