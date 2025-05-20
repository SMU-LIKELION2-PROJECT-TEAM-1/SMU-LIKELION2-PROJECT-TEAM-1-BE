package com.kwakmunsu.likelionprojectteam1.domain.comment.service;

import com.kwakmunsu.likelionprojectteam1.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentCommandService {

    private final CommentRepository commentRepository;

}