package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import eu.medsea.mimeutil.MimeUtil;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.dnu.filestorage.utils.FileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author demyura
 * @since 01.12.14
 */
@Controller
@Api(value = "files", description = "Endpoint for file management")
public class FileController {
    @Autowired
    @Qualifier("hdfsFileUploader")
    private FileUploader fileUploader;

    private Tika tika = new Tika();
    private MimeUtil mimeUtil = new MimeUtil();

    @RequestMapping("/files")
    public void getFile(@RequestParam("fileName") String fileName,
                        HttpServletResponse response) {
        try {
            InputStream is = fileUploader.getFile(fileName);
            if (is != null) {
                response.setContentType("application/force-download");
                response.setContentLength(is.available());
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
                IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
                is.close();

                /*HttpHeaders headers = new HttpHeaders();
                headers.setContentDispositionFormData("attachment", fileName);
                headers.setContentLength(is.available());
                headers.set*/

//                return new HttpEntity<>(new InputStreamResource(is), headers);
            }
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }
}
