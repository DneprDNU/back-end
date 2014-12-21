package org.dnu.filestorage.utils;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testApplicationContext.xml")
public class FileUploaderTest {
    @Autowired
    FileUploader fileUploader;
    private Logger logger = Logger.getLogger(FileUploaderTest.class);

    @Test
    public void testUploadFile() throws Exception {
        MultipartFile multipartFile = new MockMultipartFile("testFile", "content".getBytes());
        String url = fileUploader.uploadFile(multipartFile);
        logger.error(url);
        assertNotNull(fileUploader.getFile(url));
        fileUploader.removeFile(url);
    }


}