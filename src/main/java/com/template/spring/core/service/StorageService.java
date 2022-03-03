package com.template.spring.core.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Objects;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private final Log logger = LogFactory.getLog(StorageService.class);

    @Autowired
    private AmazonS3 s3Client;

    public void uploadMultipartFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        uploadFile(fileObj, file.getOriginalFilename(), false);
    }

    public void uploadFile(File fileObj, String originalName, boolean isKey) {
        String fileName;
        if (isKey) {
            fileName = originalName;
        } else {
            fileName = System.currentTimeMillis() + "_" + originalName;
        }
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        logger.info("File uploaded : " + fileName);
    }

    @SneakyThrows
    public void uploadInputStream(InputStream inputStream, String fileName) {
        byte[] bytes = IOUtils.toByteArray(inputStream);
        uploadBytes(bytes, fileName);
        logger.info("File uploaded : " + fileName);
    }

    @SneakyThrows
    public void uploadBytes(byte[] bytes, String fileName) {
        if (s3Client.doesObjectExist(fileName, ".png")) {
            throw new FileAlreadyExistsException("File already exist with this key.");
        }
        var metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, byteArrayInputStream, metadata));
        logger.info("File uploaded : " + fileName);
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public String getBucketLocation() {
        return String.format("https://%s.s3.%s.amazonaws.com",
                bucketName, s3Client.getBucketLocation(bucketName));
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            logger.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}