package com.kwakmunsu.likelionprojectteam1.domain.image;

import com.kwakmunsu.likelionprojectteam1.domain.recipe.entity.Recipe;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
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
    private final RecipeRepository recipeRepository;

    @Transactional
    public void upload(MultipartFile files) {
        // TODO: 삭제 예정
        Recipe recipe = recipeRepository.create(new Recipe());
        String fileName = s3Provider.uploadImage(files);
        Image image = Image.builder()
                .recipe(recipe)
                .name(fileName)
                .build();
        imageRepository.create(image);
    }

}