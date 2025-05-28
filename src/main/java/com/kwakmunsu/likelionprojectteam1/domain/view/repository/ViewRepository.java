package com.kwakmunsu.likelionprojectteam1.domain.view.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ViewRepository {

    private final ViewJpaRepository viewJpaRepository;

}