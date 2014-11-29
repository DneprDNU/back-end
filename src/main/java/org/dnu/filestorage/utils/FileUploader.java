package org.dnu.filestorage.utils;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUploader {

    public static final String FOLDER = "/resources/";

    public static String uploadFile(HttpServletRequest request, MultipartFile multipartFile) throws IOException {


        File f = new File(request.getRealPath("") + FOLDER + multipartFile.getOriginalFilename());

        int i = 0;
        while (f.exists()) {
            i++;
            f = new File(request.getRealPath("") + FOLDER + i + multipartFile.getOriginalFilename());
        }

        byte[] bytes = multipartFile.getBytes();
        BufferedOutputStream stream =
                new BufferedOutputStream(new FileOutputStream(f));
        stream.write(bytes);
        stream.close();

        if (i != 0) {
            return FOLDER + i + multipartFile.getOriginalFilename();
        } else {
            return FOLDER + multipartFile.getOriginalFilename();
        }
    }
}
