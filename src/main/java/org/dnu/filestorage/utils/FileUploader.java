package org.dnu.filestorage.utils;


import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileUploader {

    InputStream getFile(String fileName);

    String uploadFile(MultipartFile multipartFile);

    void removeFile(String url);
}
