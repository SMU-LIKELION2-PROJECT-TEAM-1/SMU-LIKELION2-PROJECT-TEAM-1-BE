package com.kwakmunsu.likelionprojectteam1.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "grade", nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "point")
    private int point;

    @Column(name = "social_id", unique = true, nullable = false)
    private String socialId;

    @Column(name = "social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Builder
    private Member(
            String email,
            String nickname,
            String profileUrl,
            Role role,
            Grade grade,
            int point,
            String socialId,
            SocialType socialType
    ) {
        this.email = email;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.role = role;
        this.grade = grade;
        this.point = point;
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public static Member createMember(
            String email,
            String nickname,
            String profileUrl,
            String socialId,
            SocialType socialType
    ) {
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .profileUrl(profileUrl)
                .role(Role.MEMBER)
                .grade(Grade.SEEDLING_CHEF)
                .point(0)
                .socialId(socialId)
                .socialType(socialType)
                .build();
    }

    public void updateRefreshToken(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
    }

    public void updateEmail(String newEmail) {
        this.email = newEmail;
    }

    public void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void initializeRefreshToken() {
        this.refreshToken = null;
    }

}