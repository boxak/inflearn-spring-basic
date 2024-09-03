package com.study.inflearn.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // -> 자동으로 Bean 으로 등록된다. 따라서 자동 의존관계 주입이 필요하다.
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) {

		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	// 테스트 용도
	public MemberRepository getMemberRepository() {
		return this.memberRepository;
	}
}
