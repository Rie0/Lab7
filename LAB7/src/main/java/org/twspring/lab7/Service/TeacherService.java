package org.twspring.lab7.Service;

import org.springframework.stereotype.Service;
import org.twspring.lab7.Model.Teacher;

import java.util.ArrayList;

@Service
public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    //Get
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }
    public ArrayList getTeachersByTitle(String title) {
        ArrayList<Teacher> foundTeachers = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getTitle().equalsIgnoreCase(title)) {
                foundTeachers.add(teacher);
            }
        }
        return foundTeachers;
    }
    public Teacher getTeacherByName(String name) {
        for (Teacher teacher : teachers) {
            if (teacher.getName().equalsIgnoreCase(name)) {
                return teacher;
            }
        }
        return null;
    }

    //post
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    //update
    public boolean updateTeacher(int id, Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }
    //delete
    public boolean deleteTeacher(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                teachers.remove(teacher);
                return true;
            }
        }
        return false;
    }
}
