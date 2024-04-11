package com.tpe.service;

import com.tpe.domain.Course;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    // Repository
    @Autowired
    private CourseRepository repository;

    @Override
    public void saveCourse(Course course) {
        repository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return repository.getAll();
    }

    @Override
    public Course findCourseById(Long id) {
        Course course = repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Resource Not Found With Given ID: "+id)
        );

        return course;

    }

    @Override
    public void deleteCourse(Long id) {
        repository.delete(id);
    }
}
