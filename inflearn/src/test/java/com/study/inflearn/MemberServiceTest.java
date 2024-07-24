package com.study.inflearn;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.study.inflearn.member.Grade;
import com.study.inflearn.member.Member;
import com.study.inflearn.member.MemberService;

public class MemberServiceTest {

	MemberService memberService;

	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		this.memberService = appConfig.memberService();
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
