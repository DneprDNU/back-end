package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.model.*;
import org.dnu.filestorage.data.service.*;
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
public class SubjectServiceImplTest {
    @Autowired
    SubjectService subjectService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    SpecialityService specialityService;

    @Autowired
    LinkingEntityService linkingEntityService;

    @Test
    public void testSubjectsWithResources() throws Exception {
        Resource resource = new Resource("r1", "y", "a", "d", "r", "i");
        resource = resourceService.create(resource);

        Teacher teacher = new Teacher("a");
        teacher = teacherService.create(teacher);

        Speciality speciality = new Speciality("a", "a");
        speciality = specialityService.create(speciality);

        Subject subject = new Subject("subject one");
        subject.addResource(resource);
        subject = subjectService.create(subject);

        LinkingEntity link = new LinkingEntity(speciality, subject, teacher);
        linkingEntityService.create(link);


        Assert.assertEquals(1, subjectService.get(subject.getId()).getResources().size());
        Assert.assertEquals(1, resourceService.get(resource.getId()).getSubjects().size());
        Assert.assertEquals(1, subject.getLinks().size());
        Assert.assertEquals(1, teacher.getLinks().size());
        Assert.assertEquals(1, speciality.getLinks().size());

        subjectService.remove(subject.getId());

        Assert.assertEquals(0, resourceService.get(resource.getId()).getSubjects().size());
        Assert.assertEquals(0, linkingEntityService.list().size());
    }
}