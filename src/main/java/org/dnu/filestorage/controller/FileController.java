package org.dnu.filestorage.controller;

import com.wordnik.swagger.annotations.Api;
import eu.medsea.mimeutil.MimeType;
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
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;

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
                String contentType = "";
                MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
                Collection mimeTypes = MimeUtil.getMimeTypes(fileName);
                if (mimeTypes.isEmpty()) {
                    contentType = "text/plain";
                } else {
                    Iterator iterator = mimeTypes.iterator();
                    MimeType mimeType = (MimeType) iterator.next();
                    contentType = mimeType.getMediaType() + "/" + mimeType.getSubType();
                }

                response.setContentType(contentType);
                response.setContentLength(is.available());
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition",
                        URLEncoder.encode("attachment; filename=\"" + fileName + "\"", "UTF-8"));
                response.flushBuffer();
                IOUtils.copy(is, response.getOutputStream());
                response.getOutputStream().close();
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
