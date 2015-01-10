package org.dnu.filestorage.service.dao.mock;

import org.dnu.filestorage.model.*;
import org.dnu.filestorage.search.ResourceSearchRepository;
import org.dnu.filestorage.service.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
                           SubjectDAO subjectDAO, TeacherDAO teacherDAO, ResourceDAO resourceDAO, UserDAO userDAO,
                           LinkingEntityDAO linkingEntityDAO, CategoryDAO categoryDAO, ResourceSearchRepository resourceSearchRepository) {


        init(facultyDAO, departmentDAO, specialityDAO, subjectDAO, teacherDAO, resourceDAO, userDAO, linkingEntityDAO,
                categoryDAO, resourceSearchRepository);

    }

    public void init(FacultyDAO facultyDAO, DepartmentDAO departmentDAO, SpecialityDAO specialityDAO, SubjectDAO subjectDAO, TeacherDAO teacherDAO, ResourceDAO resourceDAO, UserDAO userDAO, LinkingEntityDAO linkingEntityDAO, CategoryDAO categoryDAO, ResourceSearchRepository resourceSearchRepository) {
        userDAO.create(new User("admin", "password", true, "ROLE_ADMIN"));
        userDAO.create(new User("user", "password", true, "ROLE_USER"));
        userDAO.create(new User("superadmin", "password", true, "ROLE_SUPERADMIN"));

        Teacher teacher1 = teacherDAO.create(new Teacher("Teacher 1"));
        Teacher teacher2 = teacherDAO.create(new Teacher("Teacher 2"));
        Teacher teacher3 = teacherDAO.create(new Teacher("Teacher 3"));
        Subject subject1 = subjectDAO.create(new Subject("Subject 1"));
        Subject subject2 = subjectDAO.create(new Subject("Subject 2"));
        Subject subject3 = subjectDAO.create(new Subject("Subject 3"));

        Speciality speciality1 = specialityDAO.create(new Speciality("Speciality 1", "code 1"));
        Speciality speciality2 = specialityDAO.create(new Speciality("Speciality 2", "code 2"));
        Speciality speciality = specialityDAO.create(new Speciality("Speciality 3", "code 3"));

        LinkingEntity linkingEntity1 = linkingEntityDAO.create(new LinkingEntity());
        LinkingEntity linkingEntity2 = linkingEntityDAO.create(new LinkingEntity());
        LinkingEntity linkingEntity3 = linkingEntityDAO.create(new LinkingEntity());

        linkingEntity1.setSubject(subject1);
        linkingEntity1.setSpeciality(speciality);
        linkingEntity1.setTeacher(teacher1);
        linkingEntityDAO.update(linkingEntity1);

        linkingEntity2.setSubject(subject1);
        linkingEntity2.setSpeciality(speciality1);
        linkingEntity2.setTeacher(teacher1);
        linkingEntityDAO.update(linkingEntity2);

        linkingEntity3.setSubject(subject2);
        linkingEntity3.setSpeciality(speciality);
        linkingEntity3.setTeacher(teacher1);
        linkingEntityDAO.update(linkingEntity3);


        departmentDAO.create(new Department("Department 1", "dep.1"));
        departmentDAO.create(new Department("Department 2", "dep.2"));
        departmentDAO.create(new Department("Department 3", "dep.3"));
        Department department4 = departmentDAO.create(new Department("Department 4", "dep.4"));
        department4.getSpecialities().add(speciality);
        department4.getSpecialities().add(speciality1);
        department4.getSpecialities().add(speciality2);
        speciality.getDepartments().add(department4);
        speciality1.getDepartments().add(department4);
        speciality2.getDepartments().add(department4);
        department4.getEmployees().add(teacher1);
        department4.getEmployees().add(teacher2);
        department4.getEmployees().add(teacher3);
        departmentDAO.update(department4);


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

        Category category = categoryDAO.create(new Category());
        category.setName("Category 1");
        categoryDAO.update(category);

        // Some basic test Resources.
        Resource resource1 = new Resource("Internet technology konspekt", "2014"
                , "Yaroslav Kharchenko", "Description", "", "");
        Resource resource2 = new Resource("Internet technology konspekt", "2014"
                , "Yaroslav Kharchenko", "Description", "", "");
        Resource resource3 = new Resource("Internet technology konspekt", "2014"
                , "Yaroslav Kharchenko", "Description", "", "");

        resourceSearchRepository.clearIndex("resources_cluster");
        try {
            resourceSearchRepository.index(resource1);
            resourceSearchRepository.index(resource2);
            resourceSearchRepository.index(resource3);
        } catch (IOException e) {
            e.printStackTrace();
        }


        resource1 = resourceDAO.create(resource1);
        resourceDAO.create(resource2);
        resourceDAO.create(resource3);

        category.addResource(resource1);
        categoryDAO.update(category);

    }
}
