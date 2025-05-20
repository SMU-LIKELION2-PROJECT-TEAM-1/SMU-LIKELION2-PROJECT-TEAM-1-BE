package com.kwakmunsu.likelionprojectteam1.domain.member.repository;

import com.kwakmunsu.likelionprojectteam1.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

}