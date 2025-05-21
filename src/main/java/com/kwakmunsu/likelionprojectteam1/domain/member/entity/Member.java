package com.kwakmunsu.likelionprojectteam1.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "members")
@Entity
public class Member {

    @Id
    private Long id;

}