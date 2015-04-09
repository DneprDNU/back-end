package org.dnu.filestorage.mock;

import org.dnu.filestorage.data.dao.*;
import org.dnu.filestorage.data.model.*;
import org.dnu.filestorage.search.ResourceSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author demyura
 * @since 14.11.14
 */
@Service
@DependsOn("transactionManager")
public class MockDataService {

    private Random random = new Random();


    @Autowired

    public MockDataService(FacultyDAO facultyDAO, DepartmentDAO departmentDAO, SpecialityDAO specialityDAO,
                           SubjectDAO subjectDAO, TeacherDAO teacherDAO, ResourceDAO resourceDAO, UserDAO userDAO,
                           LinkingEntityDAO linkingEntityDAO, CategoryDAO categoryDAO, FreeResourceDAO freeResourceDAO,
                           ResourceSearchRepository resourceSearchRepository) {


        init(facultyDAO, departmentDAO, specialityDAO, subjectDAO, teacherDAO, resourceDAO, userDAO, linkingEntityDAO,
                categoryDAO, resourceSearchRepository, freeResourceDAO);

    }

    public void init(FacultyDAO facultyDAO, DepartmentDAO departmentDAO, SpecialityDAO specialityDAO,
                     SubjectDAO subjectDAO, TeacherDAO teacherDAO, ResourceDAO resourceDAO, UserDAO userDAO,
                     LinkingEntityDAO linkingEntityDAO, CategoryDAO categoryDAO,
                     ResourceSearchRepository resourceSearchRepository, FreeResourceDAO freeResourceDAO) {
        userDAO.create(new User("admin", "password", true, "ROLE_ADMIN"));
        userDAO.create(new User("user", "password", true, "ROLE_USER"));
        userDAO.create(new User("superadmin", "password", true, "ROLE_SUPERADMIN"));

    }
}
