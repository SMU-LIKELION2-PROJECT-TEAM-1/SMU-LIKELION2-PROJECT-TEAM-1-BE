package com.kwakmunsu.likelionprojectteam1.domain.member.service;

import com.kwakmunsu.likelionprojectteam1.domain.comment.repository.CommentRepository;
import com.kwakmunsu.likelionprojectteam1.domain.favorites.repository.FavoritesRepository;
import com.kwakmunsu.likelionprojectteam1.domain.like.repository.LikeRepository;
import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import com.kwakmunsu.likelionprojectteam1.domain.member.entity.MyPageOption;
import com.kwakmunsu.likelionprojectteam1.domain.member.repository.MemberRepository;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.RecipeInfinityPreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.MemberInfoResponse;
import com.kwakmunsu.likelionprojectteam1.domain.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberQueryService {

    private final MemberRepository memberRepository;
    private final RecipeRepository recipeRepository;

    public Member findByRefreshToken(String refreshToken) {
        return memberRepository.findByRefreshToken(refreshToken);
    }

    public MemberInfoResponse getProfile(Long memberId) {
        Member member = memberRepository.findById(memberId);

        return MemberInfoResponse.from(
                member.getId(),
                member.getProfileUrl(),
                member.getNickname(),
                member.getEmail(),
                member.getGrade(),
                member.getPoint()
        );
    }

    public RecipeInfinityPreviewResponse getMyRecipes(Long memberId, Long lastRecipeId, MyPageOption option) {
        return recipeRepository.findByMemberId(memberId, lastRecipeId, option);
    }

}