package com.kwakmunsu.likelionprojectteam1.domain.like.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "likes")
@Entity
public class Like {

    @Id
    private Long id;

}