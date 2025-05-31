package com.kwakmunsu.likelionprojectteam1.domain.member.controller;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.MyPageOption;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.MemberCommendService;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.MemberQueryService;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.MemberInfoResponse;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.RecipeInfinityPreviewResponse;
import com.kwakmunsu.likelionprojectteam1.global.annotation.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        MemberInfoResponse response = memberQueryService.getProfile(memberId);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/me")
    public ResponseEntity<MemberInfoResponse> getMyProfile(@AuthMember Long memberId) {
        MemberInfoResponse response = memberQueryService.getProfile(memberId);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/me/recipes")
    public ResponseEntity<RecipeInfinityPreviewResponse> getMyRecipes(
            @AuthMember Long memberId,
            @RequestParam(value = "lastRecipeId", required = false) Long lastRecipeId,
            @RequestParam(value = "option") MyPageOption option
    ) {
        RecipeInfinityPreviewResponse response = memberQueryService.getMyRecipes(memberId, lastRecipeId, option);

        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }

}