package org.dnu.filestorage.service.dao.impl;

import org.dnu.filestorage.model.Department;
import org.dnu.filestorage.model.Faculty;
import org.dnu.filestorage.model.Speciality;
import org.dnu.filestorage.service.dao.DepartmentDAO;
import org.dnu.filestorage.service.dao.FacultyDAO;
import org.dnu.filestorage.service.dao.SpecialityDAO;
import org.dnu.filestorage.service.dao.SubjectDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

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
    }
}