package com.kwakmunsu.likelionprojectteam1.infrastructure.s3.service;

import com.kwakmunsu.likelionprojectteam1.global.exception.InternalServerException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import com.kwakmunsu.likelionprojectteam1.infrastructure.s3.validator.ImageValidator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Provider {

    private final S3Client s3Client;
    private final ImageValidator imageValidator;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.dir}")
    private String dir;

    public List<String> uploadImages(List<MultipartFile> files) {
        List<String> images = new ArrayList<>();
        for (MultipartFile file : files) {
            imageValidator.validateFile(file);
            String image = uploadImage(file);
            images.add(image);
        }

        return images;
    }

    public String uploadImage(MultipartFile file) {
        String fileName = createFileName(file);
        uploadImageToS3(file, fileName, s3Client);

        return fileName;
    }

    public void deleteImages(List<String> files) {
        for (String file : files) {
            deleteImage(file);
        }
    }

    private void deleteImage(String imageUrl) {
        log.info("삭제할 이미지 url: {}", imageUrl);
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(imageUrl)
                    .build();
            s3Client.deleteObject(deleteObjectRequest);
        } catch (S3Exception e) {
            throw new InternalServerException("삭제 실패");
        } catch (Exception ex) {
            throw new InternalServerException(ErrorMessage.AWS_S3_ERROR.getMessage());
        }
    }

    private void uploadImageToS3(MultipartFile file, String fileName, S3Client s3Client) {
        try {
            RequestBody requestBody = RequestBody.fromBytes(file.getBytes());
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(fileName)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();
            s3Client.putObject(putObjectRequest, requestBody);
        } catch (IOException e) {
            throw new InternalServerException(ErrorMessage.FAILED_TO_UPLOAD_FILE.getMessage());
        }
    }

    // 이미지 파일 이름 중복 예방으로 UUID 사용.
    private String createFileName(MultipartFile file) {
        return dir + UUID.randomUUID() + "-" + file.getOriginalFilename();
    }

}