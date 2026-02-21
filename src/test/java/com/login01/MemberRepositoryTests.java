package com.login01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.login01.domain.Member;
import com.login01.repository.MemberRepository;

@SpringBootTest
public class MemberRepositoryTests {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void testInsertMember() {
		for (int i = 0; i < 10; i++) {
			Member member = Member.builder()
					.email("user" + i + "@jjjj.com")
					.password(passwordEncoder.encode("1234"))
					.role("USER")
					.build();
			memberRepository.save(member);
		}
	}
	
	@Test
	public void dd() {
			Member member = Member.builder()
					.email("admin1@jjjj.com")
					.password(passwordEncoder.encode("1234"))
					.role("ADMIN")
					.build();
			memberRepository.save(member);
		}

}
