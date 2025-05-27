package com.kwakmunsu.likelionprojectteam1.domain.like.service;

import com.kwakmunsu.likelionprojectteam1.domain.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeCommandService {

    private final LikeRepository likeRepository;

}