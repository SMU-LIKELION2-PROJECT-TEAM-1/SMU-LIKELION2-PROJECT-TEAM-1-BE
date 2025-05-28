package com.kwakmunsu.likelionprojectteam1.domain.image;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ImageRepository {

    private final ImageJpaRepository imageJpaRepository;

    public void create(Image image) {
        imageJpaRepository.save(image);
    }

    public List<String> findByRecipeId(Long recipeId) {
        List<Image> images = imageJpaRepository.findByRecipeId(recipeId);

        return images.stream()
                .map(Image::getName)
                .toList();
    }

    public void deleteByName(String name) {
        imageJpaRepository.deleteByName(name);
    }

    public void deleteByRecipeId(Long recipeId) {
        imageJpaRepository.deleteByRecipeId(recipeId);
    }

}