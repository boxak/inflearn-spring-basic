package com.study.inflearn.scan;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.inflearn.AutoAppConfig;
import com.study.inflearn.member.MemberService;

public class AutoAppConfigTest {

	@Test
	void basicScan() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AutoAppConfig.class);

		MemberService memberService = ctx.getBean(MemberService.class);

		assertThat(memberService).isInstanceOf(MemberService.class);

	}

}
