package com.study.inflearn;

import com.study.inflearn.member.Grade;
import com.study.inflearn.member.Member;
import com.study.inflearn.member.MemberService;

public class MemberApp {
	public static void main(String[] args) {

		AppConfig appConfig = new AppConfig();

		MemberService memberService = appConfig.memberService();

		Member member = new Member(1L, "memberA", Grade.VIP);

		memberService.join(member);

		Member findMember = memberService.findMember(1L);

		System.out.println("new member = " + member.getName());
		System.out.println("find member = " + findMember.getName());
	}
}
