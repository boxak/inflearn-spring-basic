package com.study.inflearn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.study.inflearn.discount.DiscountPolicy;
import com.study.inflearn.discount.RateDiscountPolicy;
import com.study.inflearn.member.MemberRepository;
import com.study.inflearn.member.MemberService;
import com.study.inflearn.member.MemberServiceImpl;
import com.study.inflearn.member.MemoryMemberRepository;
import com.study.inflearn.order.OrderService;
import com.study.inflearn.order.OrderServiceImpl;

/*
 * 전체 앱의 구현체 주입을 담당
 */

@Configuration
public class AppConfig {
	// 생성자 주입
	@Bean
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	@Bean
	public DiscountPolicy discountPolicy() {
//		return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}
}
