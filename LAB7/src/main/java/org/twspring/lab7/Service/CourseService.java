package org.twspring.lab7.Service;

import org.springframework.stereotype.Service;
import org.twspring.lab7.Model.Course;

import java.util.ArrayList;

@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList<>();

    //Gets
    public ArrayList<Course> getCourses() {
        return courses;
    }

    public Course getCourseById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public ArrayList<Course> getCoursesByMinimalDuration(Integer minimalDuration){
        ArrayList<Course> foundCourses = new ArrayList<>();

        for (Course course : courses) {
            if (course.getDuration_in_weeks()>=minimalDuration) {
                foundCourses.add(course);
            }
        }
        return foundCourses;
    }
    //Post
    public void addCourse(Course course) {
        courses.add(course);
    }
    //update
    public boolean updateCourse(int id, Course course) {

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }

    //Delete
    public boolean deleteCourse(int id) {
        for (Course foundCourse : courses) {
            if (foundCourse.getId() == id) {
                courses.remove(foundCourse);
                return true;
            }
        }
        return false;
    }
}
