package com.example.shopping.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByUserId(String userid);

}
