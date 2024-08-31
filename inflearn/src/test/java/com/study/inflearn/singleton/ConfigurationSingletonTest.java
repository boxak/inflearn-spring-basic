package com.study.inflearn.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.inflearn.AppConfig;
import com.study.inflearn.member.MemberRepository;
import com.study.inflearn.member.MemberServiceImpl;
import com.study.inflearn.order.OrderServiceImpl;

public class ConfigurationSingletonTest {

	@Test
	void configurationTest() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		MemberServiceImpl memberService = ctx.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ctx.getBean("orderService", OrderServiceImpl.class);

		MemberRepository memberRepository = ctx.getBean("memberRepository", MemberRepository.class);

		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();

		System.out.println("memberService -> memberRepository1 = " + memberRepository1);
		System.out.println("orderService -> memberRepository2 = " + memberRepository2);
		System.out.println("memberRepository = " + memberRepository);

		assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
		assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
	}

	@Test
	void configurationDeep() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ctx.getBean(AppConfig.class);

		System.out.println("bean = " + bean.getClass());
	}

}
