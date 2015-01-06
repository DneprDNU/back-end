package org.dnu.filestorage.service.dao.mock;

import org.dnu.filestorage.model.*;
import org.dnu.filestorage.service.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@DependsOn("transactionManager")
public class MockDataService {

    @Autowired
    public MockDataService(FacultyDAO facultyDAO, DepartmentDAO departmentDAO, SpecialityDAO specialityDAO,
                           SubjectDAO subjectDAO, TeacherDAO teacherDAO, ResourceDAO resourceDAO, UserDAO userDAO) {


        userDAO.create(new User("admin", "password", true, "ROLE_ADMIN"));
        userDAO.create(new User("user", "password", true, "ROLE_USER"));
        userDAO.create(new User("superadmin", "password", true, "ROLE_SUPERADMIN"));

        teacherDAO.create(new Teacher("Teacher 1"));
        teacherDAO.create(new Teacher("Teacher 2"));
        teacherDAO.create(new Teacher("Teacher 3"));
        Subject subject1 = new Subject("Subject 1");
        subject1 = subjectDAO.create(subject1);
        subjectDAO.create(new Subject("Subject 2"));
        subjectDAO.create(new Subject("Subject 3"));

        specialityDAO.create(new Speciality("Speciality 1", "code 1"));
        specialityDAO.create(new Speciality("Speciality 2", "code 2"));
        Speciality speciality = new Speciality("Speciality 3", "code 3");
        specialityDAO.create(speciality);

        departmentDAO.create(new Department("Department 1", "dep.1"));
        departmentDAO.create(new Department("Department 2", "dep.2"));
        departmentDAO.create(new Department("Department 3", "dep.3"));
        Department department4 = departmentDAO.create(new Department("Department 4", "dep.4"));
        department4.getSpecialities().add(speciality);
        speciality.getDepartments().add(department4);
        facultyDAO.create(new Faculty("Faculty 1", "f1", "Description 1"
                , "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg"));

        facultyDAO.create(new Faculty("Faculty 2", "f2", "Description 2"
                , "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg"));

        facultyDAO.create(new Faculty("Faculty 3", "f3", "Description 3"
                , "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg"));

        Faculty faculty4 = new Faculty("Faculty 4", "f4", "Description 4"
                , "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg");


        facultyDAO.create(faculty4);
        department4.setFaculty(faculty4);
        faculty4.setDepartments(Arrays.asList(department4));
        facultyDAO.update(faculty4);

        // Some basic test Resources.
        Resource resource1 = new Resource("Internet technology konspekt", null, Arrays.asList(subject1), "2014"
                , "Yaroslav Kharchenko", "Description", "", "");
        Resource resource2 = new Resource("Internet technology konspekt", null, null, "2014"
                , "Yaroslav Kharchenko", "Description", "", "");
        Resource resource3 = new Resource("Internet technology konspekt", null, null, "2014"
                , "Yaroslav Kharchenko", "Description", "", "");

        resourceDAO.create(resource1);
        resourceDAO.create(resource2);
        resourceDAO.create(resource3);

    }
}
