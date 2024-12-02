package com.auctionsysytem.s3bucket.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {
    private final AmazonS3 clientS3;

    @Value("${application.bucket.name}")
    private String bucketName;

    public String uploadFile(MultipartFile uploadFile) {
        String fileName = uploadFile.getOriginalFilename();
        File fileObj = convertMultipartFileToFile(uploadFile);
        clientS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return "File Uploaded: " + fileName;
    }

    private File convertMultipartFileToFile(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipart file to file", e);
        }
        return convFile;
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = clientS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            return bytes;
        } catch (IOException e) {
            log.error("Error downloading file from S3 bucket", e);
            return null;
        }
    }

    public String deleteFile(String fileName){
        clientS3.deleteObject(bucketName, fileName);
        return "File deleted: " + fileName;
    }
}
