package com.raonhaje.memorymap.member.repository;

import com.raonhaje.memorymap.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<Member> findByEmail(String email);
}
