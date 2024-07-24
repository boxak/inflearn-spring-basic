package com.study.inflearn;

import com.study.inflearn.discount.DiscountPolicy;
import com.study.inflearn.discount.FixDiscountPolicy;
import com.study.inflearn.member.MemberRepository;
import com.study.inflearn.member.MemberService;
import com.study.inflearn.member.MemberServiceImpl;
import com.study.inflearn.member.MemoryMemberRepository;
import com.study.inflearn.order.OrderService;
import com.study.inflearn.order.OrderServiceImpl;

/*
 * 전체 앱의 구현체 주입을 담당
 */
public class AppConfig {
	// 생성자 주입
	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}

	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}

	private MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

	private DiscountPolicy discountPolicy() {
		return new FixDiscountPolicy();
	}
}
