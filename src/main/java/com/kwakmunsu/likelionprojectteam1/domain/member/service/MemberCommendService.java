package com.kwakmunsu.likelionprojectteam1.domain.member.service;

import com.kwakmunsu.likelionprojectteam1.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberCommendService {

    private final MemberRepository memberRepository;

}