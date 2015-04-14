package org.dnu.filestorage.data.service.impl;

import org.dnu.filestorage.data.model.Resource;
import org.dnu.filestorage.data.model.Subject;
import org.dnu.filestorage.data.service.ResourceService;
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
public class SubjectServiceImplTest {
    @Autowired
    SubjectService subjectService;

    @Autowired
    ResourceService resourceService;

    @Test
    public void testSubjectsWithResources() throws Exception {
        Subject subject = new Subject("subject one");
        subject = subjectService.create(subject);

        Resource resource = new Resource("r1", "y", "a", "d", "r", "i");
        resource = resourceService.create(resource);

        Subject jsonSubjectFromClient = new Subject("subject two");
        jsonSubjectFromClient.setId(subject.getId());
        jsonSubjectFromClient.getResources().add(resource);

        subjectService.update(jsonSubjectFromClient);

        Assert.assertEquals(1, subjectService.get(subject.getId()).getResources().size());
        Assert.assertEquals(1, resourceService.get(resource.getId()).getSubjects().size());


        subjectService.remove(subject.getId());

        Assert.assertEquals(0, resourceService.get(resource.getId()).getSubjects().size());
    }
}