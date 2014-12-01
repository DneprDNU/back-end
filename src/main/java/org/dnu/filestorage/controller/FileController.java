package org.dnu.filestorage.controller;

import org.dnu.filestorage.utils.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author demyura
 * @since 01.12.14
 */
@Controller
public class FileController {
    @Autowired
    private FileUploader fileUploader;

    @RequestMapping("/files/{fileName}")
    public void getFile(@PathVariable("fileName") String fileName,
                        HttpServletResponse response) {
        try {
            InputStream is = fileUploader.getFile(fileName);
            if (is != null) {
                org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
            }
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
