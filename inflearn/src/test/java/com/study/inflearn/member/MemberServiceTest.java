package com.study.inflearn.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.inflearn.AppConfig;

public class MemberServiceTest {

	MemberService memberService;

	@BeforeEach
	public void beforeEach() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		memberService = ctx.getBean("memberService", MemberService.class);
	}

	@Test
	void join() {
		// given : 주어진 환경
		Member member = new Member(1L, "memberA", Grade.VIP);
		// when : 어떤 동작을 수행했을 떄
		memberService.join(member);

		Member findMember = memberService.findMember(1L);

		// then : 다음과 같이 된다.

		Assertions.assertThat(member).isEqualTo(findMember);
	}
}
