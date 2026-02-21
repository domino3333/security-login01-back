package com.login01.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login01.security.LoginRequest;
import com.login01.util.JwtProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest dto) {

		try {
			// "이 아이디랑 비밀번호가 진짜 맞는지 검증해줘"
			// 라고 Spring Security에게 요청하는 코드.
			// 여기서 dto로 받은 것을 객체로 만들어서 내부적으로 UserDetailsService 를 호출한다.
			// repository의 findbyemail로 db에 가서 조회해서 비밀번호를 비교해줌
			// 성공하면 인증된 Authentication 객체 반환
			// 실패하면 예외 발생 (BadCredentialsException)
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
			
			String token = jwtProvider.createToken(authentication);
			return ResponseEntity.ok(Map.of("success", true, "message", "로그인 성공"));

		} catch (AuthenticationException e) {

			// BadCredentialsException 포함
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호가 올바르지 않습니다.");
		}
	}
}