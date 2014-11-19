package org.dnu.filestorage.service.dao.mock;

import org.dnu.filestorage.model.*;
import org.dnu.filestorage.service.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@DependsOn("transactionManager")
public class MockDataService {

    @Autowired
    public MockDataService(FacultyDAO facultyDAO, DepartmentDAO departmentDAO, SpecialityDAO specialityDAO,
                           SubjectDAO subjectDAO, TeacherDAO teacherDAO) {
        teacherDAO.saveOfUpdate(new Teacher("Teacher 1"));
        teacherDAO.saveOfUpdate(new Teacher("Teacher 2"));
        teacherDAO.saveOfUpdate(new Teacher("Teacher 3"));

        subjectDAO.saveOfUpdate(new Subject("Subject 1"));
        subjectDAO.saveOfUpdate(new Subject("Subject 2"));
        subjectDAO.saveOfUpdate(new Subject("Subject 3"));

        specialityDAO.saveOfUpdate(new Speciality("Speciality 1", "code 1"));
        specialityDAO.saveOfUpdate(new Speciality("Speciality 2", "code 2"));
        specialityDAO.saveOfUpdate(new Speciality("Speciality 3", "code 3"));

        departmentDAO.saveOfUpdate(new Department("Department 1", "dep.1"));
        departmentDAO.saveOfUpdate(new Department("Department 2", "dep.2"));
        departmentDAO.saveOfUpdate(new Department("Department 3", "dep.3"));

        facultyDAO.saveOfUpdate(new Faculty("Faculty 1", "f1", "Description 1"));
        facultyDAO.saveOfUpdate(new Faculty("Faculty 2", "f2", "Description 2"));
        facultyDAO.saveOfUpdate(new Faculty("Faculty 3", "f3", "Description 3"));
    }
}