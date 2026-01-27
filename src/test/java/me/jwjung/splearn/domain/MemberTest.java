package me.jwjung.splearn.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemberTest {

	@Test
	void createMember() {
		Member member = new Member("jwjung@splearn.app", "June", "secret");
		assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
	}
}