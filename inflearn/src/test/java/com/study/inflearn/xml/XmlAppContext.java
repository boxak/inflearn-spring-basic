package com.study.inflearn.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.study.inflearn.member.MemberService;

public class XmlAppContext {

	@Test
	void xmlAppContext() {
		ApplicationContext ctx = new GenericXmlApplicationContext("appConfig.xml");
		MemberService memberService = ctx.getBean("memberService", MemberService.class);
		assertThat(memberService).isInstanceOf(MemberService.class);

		((ConfigurableApplicationContext) ctx).close();
	}

}
