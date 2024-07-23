package com.study.inflearn;

import com.study.inflearn.member.Grade;
import com.study.inflearn.member.Member;
import com.study.inflearn.member.MemberService;
import com.study.inflearn.member.MemberServiceImpl;
import com.study.inflearn.order.Order;
import com.study.inflearn.order.OrderService;
import com.study.inflearn.order.OrderServiceImpl;

public class OrderApp {
	public static void main(String[] args) {
		MemberService memberService = new MemberServiceImpl();
		OrderService orderService = new OrderServiceImpl();
		
		Long memberId = 1L;
		Member member = new Member(memberId, "memberA", Grade.VIP);
		memberService.join(member);
		
		Order order = orderService.createOrder(memberId, "itemA", 10000);
		
		System.out.println("order = " + order);
		System.out.println("order.caclulatePrice = " + order.calculatePrice());
	}
}
