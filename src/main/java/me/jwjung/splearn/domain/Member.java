package me.jwjung.splearn.domain;

import java.util.Objects;

import lombok.Getter;

@Getter
public class Member {
	private String email;

	private String nickname;

	private String passwordHash;

	private MemberStatus status;

	public Member(final String email, final String nickname, final String passwordHash) {
		this.email = Objects.requireNonNull(email);
		this.nickname = Objects.requireNonNull(nickname);
		this.passwordHash = Objects.requireNonNull(passwordHash);
		this.status = MemberStatus.PENDING;
	}
}
