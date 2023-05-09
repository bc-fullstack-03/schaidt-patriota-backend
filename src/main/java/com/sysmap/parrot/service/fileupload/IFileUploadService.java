package com.sysmap.parrot.service.fileupload;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {
    String upload(MultipartFile file, String fileName) throws Exception;
}
