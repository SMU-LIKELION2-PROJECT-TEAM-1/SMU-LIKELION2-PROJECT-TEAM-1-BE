package com.kwakmunsu.likelionprojectteam1.domain.favorites.repository;

import com.kwakmunsu.likelionprojectteam1.domain.favorites.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritesJpaRepository extends JpaRepository<Favorites, Long> {

}