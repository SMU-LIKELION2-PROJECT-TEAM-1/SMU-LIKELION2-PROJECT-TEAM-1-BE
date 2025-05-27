package com.kwakmunsu.likelionprojectteam1.domain.tag.repository;

import com.kwakmunsu.likelionprojectteam1.domain.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagJpaRepository extends JpaRepository<Tag, Long> {

}