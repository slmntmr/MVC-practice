package com.tpe.controller;

import com.tpe.domain.Course;
import com.tpe.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/courses") // Courses altında gelen request'leri, bu controller ile map'le.
public class CourseController {

    // Service
    @Autowired
    private CourseService service;

    // Display Form
    @GetMapping("/form") // ...com/courses/form
    public String displayCourseForm(@ModelAttribute("course")Course course){
        return "courseForm"; // courseForm.jsp
    }

    // Submit Button /  Save Course
    @PostMapping("/saveCourse")
    public String createCourse(@ModelAttribute("course") Course course, BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return "courseForm";

        service.saveCourse(course);

        return "redirect:/courses"; // Tüm kurslar sayfasına yönlendiriliyor

    }

    @GetMapping
    public ModelAndView getCourses(){

        List<Course> courses = service.getAllCourses();

        ModelAndView mav = new ModelAndView();
        mav.addObject("courses",courses);
        mav.setViewName("courses");

        return mav;

    }

    @GetMapping("/update") // ...com/courses/update?id=1
    public String displayUpdateForm(@RequestParam("id") Long id, Model model){

        Course course = service.findCourseById(id);

        model.addAttribute("course", course);

        return "courseForm";

    }

    @GetMapping("/delete/{id}") // ...com/courses/delete/1
    public String deleteCourse(@PathVariable Long id){

        service.deleteCourse(id);

        return "redirect:/courses";

    }

}
