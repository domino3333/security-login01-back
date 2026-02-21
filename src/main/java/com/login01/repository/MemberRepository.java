package com.login01.repository;

import java.util.Optional;

import com.login01.domain.Member;

public interface MemberRepository {

    Optional<Member> findByEmail(String email);

}
