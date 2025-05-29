package com.kwakmunsu.likelionprojectteam1.domain.member.service;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import com.kwakmunsu.likelionprojectteam1.domain.member.repository.MemberRepository;
import com.kwakmunsu.likelionprojectteam1.domain.member.service.dto.response.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberQueryService {

    private final MemberRepository memberRepository;

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

}