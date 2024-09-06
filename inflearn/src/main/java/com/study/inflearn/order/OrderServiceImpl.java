package com.study.inflearn.order;

import com.study.inflearn.discount.DiscountPolicy;
import com.study.inflearn.member.Member;
import com.study.inflearn.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

	//@Autowired // 필드 주입은 테스트 등이 힘들어서 권장되지 않는다.
	private MemberRepository memberRepository;
	private DiscountPolicy discountPolicy;

	/*
	 * @Autowired 는 기본적으로 타입으로 Bean 객체를 찾아서 주입한다. 
	 * 생성자 주입 
	 * - 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다. 
	 * - 불변, 필수 의존관계에 사용
	 * - Autowired는 생성자가 1개일 경우에는 생략가능하다.
	 */
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		System.out.println("member Repository = " + memberRepository);
		System.out.println("discount Policy = " + discountPolicy);
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}

	//	@Autowired
	//	public void setMemberRepository(MemberRepository memberRepository) {
	//		System.out.println("member Repository = " + memberRepository);
	//		this.memberRepository = memberRepository;
	//	}
	//
	//	@Autowired
	//	public void setDiscountPolicy(DiscountPolicy discountPolicy) {
	//		System.out.println("discount Policy = " + discountPolicy);
	//		this.discountPolicy = discountPolicy;
	//	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		Member member = memberRepository.findById(memberId);

		int discountPrice = discountPolicy.discount(member, itemPrice);

		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return this.memberRepository;
	}
}
