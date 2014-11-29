package org.dnu.filestorage.utils;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.net.URI;

@Component
public class FileUploader {

    public static final String FOLDER = "/resources/";

    @Value("#{hdfs.url}")
    private String hdfsUrl = "";

    public String uploadFile(HttpServletRequest request, MultipartFile multipartFile) {

        try {
            Configuration configuration = new Configuration();
            FileSystem hdfs = FileSystem.get(new URI(hdfsUrl), configuration);
            Path file = new Path(hdfsUrl + FOLDER + multipartFile.getOriginalFilename());
            int i = 0;
            while (hdfs.exists(file)) {
                i++;
                file = new Path(hdfsUrl + FOLDER + multipartFile.getOriginalFilename() + i);
            }

            BufferedOutputStream os = new BufferedOutputStream(hdfs.create(file));
            os.write(multipartFile.getBytes());
            os.close();
            hdfs.close();
            return file.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}