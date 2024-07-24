package com.study.inflearn.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.study.inflearn.AppConfig;
import com.study.inflearn.member.Grade;
import com.study.inflearn.member.Member;
import com.study.inflearn.member.MemberService;

public class OrderServiceTest {

	MemberService memberService;
	OrderService orderService;

	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		this.memberService = appConfig.memberService();
		this.orderService = appConfig.orderService();
	}

	@Test
	void createOrder() {
		Long memberId = 1L;

		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);

		Order order = orderService.createOrder(memberId, "itemA", 10000);

		Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}
