package org.dnu.filestorage.data.dao.impl;

import org.dnu.filestorage.data.dao.*;
import org.dnu.filestorage.data.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testApplicationContext.xml")
public class SpecialityDAOImplTest {

    @Autowired
    FacultyDAO facultyDAO;
    @Autowired
    DepartmentDAO departmentDAO;
    @Autowired
    SpecialityDAO specialityDAO;
    @Autowired
    SubjectDAO subjectDAO;
    @Autowired
    ResourceDAO resourceDAO;
    @Autowired
    CategoryDAO categoryDAO;

    @Test
    public void testGetSpecialitiesByFacultyId() throws Exception {

        Faculty faculty = new Faculty("a", "a", "a");
        Department department = new Department("b", "b");
        Speciality speciality = new Speciality("c", "c");
        department.setFaculty(faculty);
        faculty.getDepartments().add(department);
        speciality.getDepartments().add(department);
        department.getSpecialities().add(speciality);

        facultyDAO.create(faculty);
        assertEquals(1, specialityDAO.getSpecialitiesByFacultyId(faculty.getId()).size());
        assertNotNull(facultyDAO.getFacultyWithRelations(faculty.getId()));
        assertNotNull(departmentDAO.getDepartmentWithRelations(department.getId()));

    }

    @Test
    public void testRequestResourcesByCategory() throws Exception {
        Category category = new Category();
        Resource resource = resourceDAO.create(new Resource());
        category.addResource(resource);


        category = categoryDAO.create(category);
        resourceDAO.update(resource);
        assertEquals(1, resourceDAO.listByCategoryId(category.getId()).size());
//        assertNotEquals(Long.valueOf(1l), category.getId());
//        assertEquals(1, resourceDAO.listByCategoryId(1l).size());
//        assertEquals(1, resourceDAO.listResourcesByTeacherIdByLinks(1l).size());
//        assertEquals(2, subjectDAO.getByDepartmentId(4l).size());
    }


    @Test
    public void testMockData() throws Exception {
        Department department = departmentDAO.getDepartmentWithRelations(1l);
        Set<Speciality> specialities = new HashSet<>(department.getSpecialities());
        assertNotEquals(0, specialities.size());
        assertEquals(department.getSpecialities().size(), specialities.size());

        Speciality speciality = specialityDAO.get(department.getSpecialities().get(0).getId());
        assertNotEquals(0, speciality.getSupervisors().size());
        assertEquals(speciality.getSupervisors().size(), new HashSet<>(speciality.getSupervisors()).size());
    }
}