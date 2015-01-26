package org.dnu.filestorage.data.dao.mock;

import org.dnu.filestorage.data.dao.*;
import org.dnu.filestorage.data.model.*;
import org.dnu.filestorage.data.service.FreeResourceCategoryService;
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
                           ResourceSearchRepository resourceSearchRepository, FreeResourceCategoryService freeResourceCategoryService) {


        init(facultyDAO, departmentDAO, specialityDAO, subjectDAO, teacherDAO, resourceDAO, userDAO, linkingEntityDAO,
                categoryDAO, resourceSearchRepository, freeResourceDAO, freeResourceCategoryService);

    }

    public void init(FacultyDAO facultyDAO, DepartmentDAO departmentDAO, SpecialityDAO specialityDAO,
                     SubjectDAO subjectDAO, TeacherDAO teacherDAO, ResourceDAO resourceDAO, UserDAO userDAO,
                     LinkingEntityDAO linkingEntityDAO, CategoryDAO categoryDAO,
                     ResourceSearchRepository resourceSearchRepository, FreeResourceDAO freeResourceDAO,
                     FreeResourceCategoryService freeResourceCategoryService) {
        userDAO.create(new User("admin", "password", true, "ROLE_ADMIN"));
        userDAO.create(new User("user", "password", true, "ROLE_USER"));
        userDAO.create(new User("superadmin", "password", true, "ROLE_SUPERADMIN"));


        List<Teacher> teacherList = new LinkedList<Teacher>();
        List<Subject> subjects = new LinkedList<Subject>();
        List<Speciality> specialities = new LinkedList<>();
        List<Resource> resources = new LinkedList<>();

        for (int s = 0; s < 10; ++s) {
            Subject subject = subjectDAO.create(new Subject("Subject " + s));
            subjects.add(subject);

            for (int c = 0; c < 5; ++c) {
                Category category = categoryDAO.create(new Category("Category " + s + " " + c));
                for (int r = 0; r < 10; ++r) {
                    Resource resource = resourceDAO.create(new Resource("Resource " + s + " " + r, "2014",
                            "Author", "Description", "resourceUrl",
                            "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg"));
                    resources.add(resource);
                    resource.addCategory(category);
                    subject.addResource(resource);
                    resourceDAO.update(resource);
                }
                subjectDAO.update(subject);
            }
        }

        for (int f = 0; f < 10; ++f) {
            Faculty faculty = facultyDAO
                    .create(new Faculty("Faculty " + f, "F" + f, "Faculty " + f + " description",
                            "http://dnu.thebodva.com/upload/b32f3d1ef28edf602362b91cb935886f.jpg"));

            for (int d = 0; d < 5; ++d) {
                Department department = departmentDAO
                        .create(new Department("Department " + d + " (Faculty " + f + ")", "D" + d + "F" + f));
                faculty.addDepartment(department);

                Teacher previousSuperviser = null;
                int teacherIndex = 1;
                for (int s = 0; s < 10; ++s) {
                    Speciality speciality = specialityDAO
                            .create(new Speciality("Speciality " + f + " " + d + " " + s,
                                    "sp." + f + " " + d + " " + s));

                    specialities.add(speciality);
                    department.addSpeciality(speciality);

                    if (random.nextBoolean() || previousSuperviser == null) {
                        Teacher teacher = teacherDAO.create(new Teacher("Teacher " + f + " " + d + " " + teacherIndex++));
                        previousSuperviser = teacher;
                        department.addEmployee(teacher);
                        speciality.addSupervisor(teacher);

//                        teacherDAO.update(teacher);
                        teacherList.add(teacher);
                    }
                }

            }
            facultyDAO.update(faculty);
        }

        for (int s = 0; s < specialities.size(); s++) {
            for (int su = 0; su < 5; su++) {
                LinkingEntity linkingEntity = linkingEntityDAO.create(new LinkingEntity());
                linkingEntity.setSpeciality(specialities.get(s));
                linkingEntity.setSubject(subjects.get(random.nextInt(subjects.size())));
                linkingEntity.setTeacher(teacherList.get(random.nextInt(teacherList.size())));
                linkingEntityDAO.update(linkingEntity);
            }
        }


        for (int c = 0; c < 5; ++c) {
            FreeResourceCategory category = freeResourceCategoryService
                    .create(new FreeResourceCategory("free resource category " + c));
            for (int i = 0; i < 10; ++i) {
                FreeResource freeResource = freeResourceDAO.create(new FreeResource("Free resource " + i, "description",
                        "google.com.ua", null));
                freeResource.addCategory(category);
                freeResourceDAO.update(freeResource);
            }
        }


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
        Resource resource1 = new Resource("Конспект", "2014"
                , "Yaroslav Kharchenko", "Description", "", "");
        Resource resource2 = new Resource("Internet technology konspekt", "2014"
                , "Yaroslav Kharchenko", "Description", "", "");
        Resource resource3 = new Resource("Internet technology konspekt", "2014"
                , "Yaroslav Kharchenko", "Description", "", "");

        resource1 = resourceDAO.create(resource1);
        resource2 = resourceDAO.create(resource2);
        resource3 = resourceDAO.create(resource3);

        resource1.setSpeciality(speciality);

        category.addResource(resource1);
        resourceDAO.update(resource1);
        categoryDAO.update(category);

        subject1.addResource(resource1);
        subjectDAO.update(subject1);

        resourceSearchRepository.clearIndex("resources_cluster");
        try {
            resourceSearchRepository.index(resourceDAO.get(1l));
            resourceSearchRepository.index(resource2);
            resourceSearchRepository.index(resource3);
            for (Resource resource : resources) {
                resourceSearchRepository.index(resource);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
