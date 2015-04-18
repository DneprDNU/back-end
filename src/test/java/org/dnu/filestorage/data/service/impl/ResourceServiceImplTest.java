package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.model.Category;
import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.data.model.Speciality;
import org.dnu.filestorage.data.model.Subject;
import org.dnu.filestorage.data.service.CategoryService;
import org.dnu.filestorage.data.service.ResourceService;
import org.dnu.filestorage.data.service.SpecialityService;
import org.dnu.filestorage.data.service.SubjectService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author demyura
 * @since 14.04.15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testApplicationContext.xml")
public class ResourceServiceImplTest {
    @Autowired
    SubjectService subjectService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    SpecialityService specialityService;

    @Test
    public void testSubjectsWithResources() throws Exception {
        Subject subject = new Subject("subject one");
        subject = subjectService.create(subject);

        Category category = new Category("category");
        category = categoryService.create(category);

        Speciality speciality = new Speciality("speciality", "sp");
        speciality = specialityService.create(speciality);

        Resource resource = new Resource("r1", "y", "a", "d", "r", "i");
        resource.addCategory(category);
        resource.addSubject(subject);
        resource.setSpeciality(speciality);
        resource = resourceService.create(resource);

        Assert.assertEquals(1, subjectService.get(subject.getId()).getResources().size());
        Assert.assertEquals(1, resourceService.get(resource.getId()).getSubjects().size());
        Assert.assertEquals(1, categoryService.get(category.getId()).getResources().size());


        resourceService.remove(resource.getId());

        Assert.assertEquals(0, categoryService.get(category.getId()).getResources().size());
        Assert.assertEquals(0, subjectService.get(subject.getId()).getResources().size());
    }
}