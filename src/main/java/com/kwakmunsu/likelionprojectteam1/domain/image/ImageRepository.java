package com.kwakmunsu.likelionprojectteam1.domain.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ImageRepository {

    private final ImageJpaRepository imageJpaRepository;

    public void create(Image image) {
        imageJpaRepository.save(image);
    }

}