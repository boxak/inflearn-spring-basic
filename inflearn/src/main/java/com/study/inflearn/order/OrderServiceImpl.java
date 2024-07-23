package com.study.inflearn.order;

import com.study.inflearn.discount.DiscountPolicy;
import com.study.inflearn.member.Member;
import com.study.inflearn.member.MemberRepository;
import com.study.inflearn.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();
	private DiscountPolicy discountPolicy;

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);

		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}
}
