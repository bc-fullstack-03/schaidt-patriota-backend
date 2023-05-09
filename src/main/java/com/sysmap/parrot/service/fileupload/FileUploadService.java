package com.sysmap.parrot.service.fileupload;

import com.sysmap.parrot.service.aws.IAwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService implements IFileUploadService{

    @Autowired
    private IAwsService iAwsService;

    public String upload(MultipartFile file, String fileName) throws Exception {
        var fileUrl = "";

        try {
            fileUrl = iAwsService.upload(file, fileName);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return fileUrl;
    }
}
