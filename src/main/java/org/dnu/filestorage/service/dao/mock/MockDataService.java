package org.dnu.filestorage.service.dao.mock;

import org.dnu.filestorage.model.*;
import org.dnu.filestorage.service.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@DependsOn("transactionManager")
public class MockDataService {

    @Autowired
    public MockDataService(FacultyDAO facultyDAO, DepartmentDAO departmentDAO, SpecialityDAO specialityDAO,
                           SubjectDAO subjectDAO, TeacherDAO teacherDAO, ResourceDAO resourceDAO) {
        teacherDAO.saveOfUpdate(new Teacher("Teacher 1"));
        teacherDAO.saveOfUpdate(new Teacher("Teacher 2"));
        teacherDAO.saveOfUpdate(new Teacher("Teacher 3"));
        Subject subject1 = new Subject("Subject 1");
        subject1 = subjectDAO.saveOfUpdate(subject1);
        subjectDAO.saveOfUpdate(new Subject("Subject 2"));
        subjectDAO.saveOfUpdate(new Subject("Subject 3"));

        specialityDAO.saveOfUpdate(new Speciality("Speciality 1", "code 1"));
        specialityDAO.saveOfUpdate(new Speciality("Speciality 2", "code 2"));
        specialityDAO.saveOfUpdate(new Speciality("Speciality 3", "code 3"));

        departmentDAO.saveOfUpdate(new Department("Department 1", "dep.1"));
        departmentDAO.saveOfUpdate(new Department("Department 2", "dep.2"));
        departmentDAO.saveOfUpdate(new Department("Department 3", "dep.3"));
        Department department4 = departmentDAO.saveOfUpdate(new Department("Department 4", "dep.4"));

        facultyDAO.saveOfUpdate(new Faculty("Faculty 1", "f1", "Description 1"
                , "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg"));

        facultyDAO.saveOfUpdate(new Faculty("Faculty 2", "f2", "Description 2"
                , "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg"));

        facultyDAO.saveOfUpdate(new Faculty("Faculty 3", "f3", "Description 3"
                , "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg"));

        Faculty faculty4 = new Faculty("Faculty 4", "f4", "Description 4"
                , "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg");

        faculty4.setDepartments(Arrays.asList(department4));
        facultyDAO.saveOfUpdate(faculty4);


        // Some basic test Resources.
        Resource resource1 = new Resource("Internet technology konspekt", null, Arrays.asList(subject1), "2014"
                , "Yaroslav Kharchenko", "Description", "", "");
        Resource resource2 = new Resource("Internet technology konspekt", null, null, "2014"
                , "Yaroslav Kharchenko", "Description", "", "");
        Resource resource3 = new Resource("Internet technology konspekt", null, null, "2014"
                , "Yaroslav Kharchenko", "Description", "", "");

        resourceDAO.saveOfUpdate(resource1);
        resourceDAO.saveOfUpdate(resource2);
        resourceDAO.saveOfUpdate(resource3);

    }
}
