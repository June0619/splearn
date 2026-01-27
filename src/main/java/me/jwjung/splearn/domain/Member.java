package me.jwjung.splearn.domain;

import lombok.Getter;

@Getter
public class Member {
	private String email;

	private String nickname;

	private String passwordHash;

	private MemberStatus status;

	public Member(final String email, final String nickname, final String passwordHash) {
		this.email = email;
		this.nickname = nickname;
		this.passwordHash = passwordHash;
		this.status = MemberStatus.PENDING;
	}
}
