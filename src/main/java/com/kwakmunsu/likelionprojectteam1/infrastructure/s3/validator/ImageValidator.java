package com.kwakmunsu.likelionprojectteam1.infrastructure.s3.validator;

import com.kwakmunsu.likelionprojectteam1.global.exception.BadRequestException;
import com.kwakmunsu.likelionprojectteam1.global.exception.NotFoundException;
import com.kwakmunsu.likelionprojectteam1.global.exception.dto.ErrorMessage;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageValidator {

    private static final List<String> WHITE_LIST = List.of("jpg", "jpeg", "png", "webp");

    public void validateFile(MultipartFile file) {
        validateFileNameNotBlank(file);
        validateExtension(file);
    }

    private void validateFileNameNotBlank(MultipartFile file) {
        if (file.isEmpty() || file.getOriginalFilename() == null) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND_FILE.getMessage());
        }
    }

    private void validateExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();

        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex == -1 || filename.endsWith(".")) {
            throw new BadRequestException(ErrorMessage.INVALID_FILE_EXTENSION.getMessage());
        }
        String extension = filename.substring(extensionIndex + 1);
        if (!WHITE_LIST.contains(extension.toLowerCase())) {
            throw new BadRequestException(ErrorMessage.INVALID_FILE_EXTENSION.getMessage());
        }
    }

}