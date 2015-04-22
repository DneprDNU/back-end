package org.dnu.filestorage.utils.impl;

import org.dnu.filestorage.utils.FileUploader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;

/**
 * @author demyura
 * @since 22.04.15
 */
@Component("resourceFileUploader")
public class ResourceFileUploader implements FileUploader {

    public static final String FOLDER = "/resources/";

    @Override
    public InputStream getFile(String fileName) {
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = URLEncoder.encode(multipartFile.getOriginalFilename(), "UTF-8");
            File f = new File(FOLDER + fileName);

            int i = 0;
            while (f.exists()) {
                i++;
                f = new File(FOLDER + i + fileName);
            }

            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(f));
            stream.write(bytes);
            stream.close();

            if (i != 0) {
                return FOLDER + i + fileName;
            } else {
                return FOLDER + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeFile(String url) {
        new File(url).deleteOnExit();
    }
}

