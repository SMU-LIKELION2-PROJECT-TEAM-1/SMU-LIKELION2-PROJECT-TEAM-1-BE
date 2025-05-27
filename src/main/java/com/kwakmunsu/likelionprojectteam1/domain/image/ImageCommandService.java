package com.kwakmunsu.likelionprojectteam1.domain.image;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.infrastructure.s3.service.S3Provider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageCommandService {

    private final S3Provider s3Provider;
    private final ImageRepository imageRepository;

    @Transactional
    public void upload(List<MultipartFile> files, Recipe recipe) {
        List<String> filenames = s3Provider.uploadImages(files);

        for (String filename : filenames) {
            log.info("name: " + filename);

            Image image = Image.builder()
                    .recipe(recipe)
                    .name(filename)
                    .build();
            imageRepository.create(image);
        }

    }

}