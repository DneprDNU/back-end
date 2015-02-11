package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.model.Department;
import org.dnu.filestorage.data.model.Faculty;
import org.dnu.filestorage.data.service.DepartmentService;
import org.dnu.filestorage.data.service.FacultyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testApplicationContext.xml")
public class FacultyServiceImplTest {
    FacultyService facultyService;
    DepartmentService departmentService;

    @Autowired
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Test
    public void testUpdate() throws Exception {
        Faculty faculty = new Faculty();
        Department department1 = departmentService.create(new Department());
        Department department2 = departmentService.create(new Department());
        faculty.addDepartment(department1);
        faculty.addDepartment(department2);
        faculty = facultyService.create(faculty);

        faculty = facultyService.get(faculty.getId());
        assertEquals(2, faculty.getDepartments().size());
        List<Department> newDepartments = new LinkedList<>();
        Department department = new Department();
        department.setId(department1.getId());
        newDepartments.add(department);
        faculty.setDepartments(newDepartments);
        facultyService.update(faculty);

        faculty = facultyService.get(faculty.getId());
        assertEquals(1, faculty.getDepartments().size());
        assertNull(departmentService.get(department2.getId()).getFaculty());
    }
}