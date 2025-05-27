package com.kwakmunsu.likelionprojectteam1.domain.member.controller;

import com.kwakmunsu.likelionprojectteam1.domain.member.service.MemberCommendService;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.MemberQueryService;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.CommentRecipePreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.FavoritesRecipePreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.LikeRecipePreviewResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.MemberInfoResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.RecipePreviewResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController extends MemberDocsController {

    private final MemberCommendService memberCommendService;
    private final MemberQueryService memberQueryService;

    @Override
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberInfoResponse> getMemberProfile(@PathVariable(name = "memberId") Long memberId) {
        return ResponseEntity.ok(MemberInfoResponse.builder()
                .profileUrl("profile-url")
                .nickname("testNickname")
                .email("test1234@gmai.com")
                .grade("GOLD")
                .point(888)
                .build()
        );
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<MemberInfoResponse> getMyProfile() {
        return ResponseEntity.ok(MemberInfoResponse.builder()
                .profileUrl("profile-url")
                .nickname("testNickname")
                .email("test1234@gmai.com")
                .grade("GOLD")
                .point(888)
                .build()
        );
    }

    @Override
    @GetMapping("/me/likes")
    public ResponseEntity<LikeRecipePreviewResponse> getLikedRecipes() {
        return ResponseEntity.ok(LikeRecipePreviewResponse.builder()
                .responses(getTestRecipePreviewResponse())
                .build());
    }

    @Override
    @GetMapping("/me/comments")
    public ResponseEntity<CommentRecipePreviewResponse> getWriteCommentRecipes() {
        return ResponseEntity.ok(CommentRecipePreviewResponse.builder()
                .responses(getTestRecipePreviewResponse())
                .build());
    }

    @Override
    @GetMapping("/me/favorites")
    public ResponseEntity<FavoritesRecipePreviewResponse> getMyFavoritesRecipes() {
        return ResponseEntity.ok(FavoritesRecipePreviewResponse.builder()
                .responses(getTestRecipePreviewResponse())
                .build());
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }

    // 추후 삭제 예정
    private static List<RecipePreviewResponse> getTestRecipePreviewResponse() {
        List<RecipePreviewResponse> responses = new ArrayList<>();
        RecipePreviewResponse testMock = RecipePreviewResponse.toTestMock();
        responses.add(testMock);
        responses.add(testMock);
        return responses;
    }

}