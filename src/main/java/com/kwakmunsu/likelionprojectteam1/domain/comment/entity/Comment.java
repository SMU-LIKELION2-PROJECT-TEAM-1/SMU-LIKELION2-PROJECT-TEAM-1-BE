package com.kwakmunsu.likelionprojectteam1.domain.comment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "comment")
@Entity
public class Comment {

    @Id
    private Long id;

}