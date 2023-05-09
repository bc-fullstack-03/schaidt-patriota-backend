package com.sysmap.parrot.service.aws;

import org.springframework.web.multipart.MultipartFile;

public interface IAwsService {
    String upload(MultipartFile multipartFile, String fileName) throws Exception;
}
