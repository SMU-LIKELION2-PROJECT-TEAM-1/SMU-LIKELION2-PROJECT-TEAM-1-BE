package com.kwakmunsu.likelionprojectteam1.domain.like.repository;

import com.kwakmunsu.likelionprojectteam1.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Like, Long> {

}