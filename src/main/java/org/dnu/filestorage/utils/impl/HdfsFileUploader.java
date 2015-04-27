package org.dnu.filestorage.utils.impl;


import org.apache.commons.io.FilenameUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.dnu.filestorage.utils.FileUploader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.URI;

@Component("hdfsFileUploader")
public class HdfsFileUploader implements FileUploader {

    public static final String FOLDER = "/resources/";

    @Value("${hdfs.url}")
    private String hdfsUrl = "";

    public InputStream getFile(String fileName) {
        try {
            Configuration configuration = new Configuration();
            FileSystem hdfs = FileSystem.get(new URI(hdfsUrl), configuration);
            Path path = new Path(FOLDER + fileName);
            return hdfs.open(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String uploadFile(MultipartFile multipartFile) {

        try {
            String fileName = ResourceFileUploader
                    .prepareFileName(multipartFile.getOriginalFilename());
            Configuration configuration = new Configuration();
            FileSystem hdfs = FileSystem.get(new URI(hdfsUrl), configuration);
            Path file = new Path(hdfsUrl + FOLDER + fileName);
            int i = 0;
            while (hdfs.exists(file)) {
                i++;
                file = new Path(hdfsUrl + FOLDER + FilenameUtils.getBaseName(fileName) + i + "."
                        + FilenameUtils.getExtension(fileName));
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

    public void removeFile(String url) {
        try {
            Configuration configuration = new Configuration();
            FileSystem hdfs = FileSystem.get(new URI(hdfsUrl), configuration);
            Path path = new Path(FOLDER + url);
            hdfs.deleteOnExit(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
