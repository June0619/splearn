package me.jwjung.splearn.domain;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

	@Test
	void createMember() {
		Member member = new Member("jwjung@splearn.app", "June", "secret");
		assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
	}

	@Test
	@DisplayName("member 생성 시 email, nickname, passwordHash 는 null 이 될 수 없다.")
	void constructorNonNull() {
		assertThatThrownBy(() -> new Member(null, "June", "secret"))
			.isInstanceOf(NullPointerException.class);
	}

	@Test
	void activate() {
		var member = new Member("june", "june", "secret");
		member.activate();
		assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVATE);
	}

	@Test
	void activateFail() {
		var member = new Member("june", "june", "secret");

		member.activate();

		assertThatThrownBy(member::activate).isInstanceOf(IllegalStateException.class);
	}

	@Test
	void deactivate() {
		var member = new Member("june", "june", "secret");

		member.activate();
		member.deactivate();

		assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
	}

	@Test
	void deactivateFail() {
		var member = new Member("june", "june", "secret");

		assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class);

		member.activate();
		member.deactivate();

		assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class);
	}
}