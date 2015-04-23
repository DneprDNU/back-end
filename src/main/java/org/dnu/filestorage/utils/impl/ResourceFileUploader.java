package org.dnu.filestorage.utils.impl;

import org.dnu.filestorage.utils.FileUploader;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${resources.dir}")
    private String resourcesBaseDir;

    public static String prepareFileName(String originalFilename) {
        return originalFilename.replaceAll("[\\s:\\\\/*?|<>&#]", "_");
    }

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
        File root = new File(resourcesBaseDir);
        if (!root.exists()) {
            try {
                root.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            String fileName = URLEncoder.encode(prepareFileName(multipartFile.getOriginalFilename()), "UTF-8");
            File f = new File(resourcesBaseDir + File.separator + fileName);

            int i = 0;
            while (f.exists()) {
                i++;
                f = new File(resourcesBaseDir + File.separator + i + fileName);
            }

            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(f));
            stream.write(bytes);
            stream.close();

            if (i != 0) {
                return i + fileName;
            } else {
                return fileName;
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

