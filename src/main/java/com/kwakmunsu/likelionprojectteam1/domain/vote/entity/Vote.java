package com.kwakmunsu.likelionprojectteam1.domain.vote.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "vote")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "challenge_id", nullable = false)
    private Long challengeId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "ingredient_id", nullable = false)
    private Long ingredientId;

    @Builder
    private Vote(Long challengeId, Long memberId, Long ingredientId) {
        this.challengeId = challengeId;
        this.memberId = memberId;
        this.ingredientId = ingredientId;
    }

}