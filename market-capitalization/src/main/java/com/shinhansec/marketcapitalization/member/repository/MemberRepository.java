package com.shinhansec.marketcapitalization.member.repository;

import com.shinhansec.marketcapitalization.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
