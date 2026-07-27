package me.jwjung.splearn.domain;


import static me.jwjung.splearn.domain.MemberFixture.createMemberRegisterRequest;
import static me.jwjung.splearn.domain.MemberFixture.createPasswordEncoder;
import static org.assertj.core.api.Assertions.*;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {
	Member member;
	PasswordEncoder passwordEncoder;

	@BeforeEach
	void setUp() {
		passwordEncoder = createPasswordEncoder();

		member = Member.register(createMemberRegisterRequest(), passwordEncoder);
	}

	@Test
	void registerMember() {
		assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
	}

	@Test
	@DisplayName("member 생성 시 email, nickname, passwordHash 는 null 이 될 수 없다.")
	void constructorNonNull() {
		assertThatThrownBy(() -> Member.register(new MemberRegisterRequest(null, "June", "secret"), null))
			.isInstanceOf(NullPointerException.class);
	}

	@Test
	void activate() {
		member.activate();
		assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVATE);
	}

	@Test
	void activateFail() {
		member.activate();

		assertThatThrownBy(member::activate).isInstanceOf(IllegalStateException.class);
	}

	@Test
	void deactivate() {
		member.activate();
		member.deactivate();

		assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
	}

	@Test
	void deactivateFail() {
		assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class);

		member.activate();
		member.deactivate();

		assertThatThrownBy(member::deactivate).isInstanceOf(IllegalStateException.class);
	}

	@Test
	void verifyPassword() {
		assertThat(member.verifyPassword("SECRET", passwordEncoder)).isTrue();
		assertThat(member.verifyPassword("hello", passwordEncoder)).isFalse();
	}

	@Test
	void changeNickname() {
		assertThat(member.getNickname()).isEqualTo("June");

		member.changeNickname("Jiwoon");

		assertThat(member.getNickname()).isEqualTo("Jiwoon");
	}

	@Test
	void changePassword() {
		member.changePassword("verysecret", passwordEncoder);
		assertThat(member.verifyPassword("VERYSECRET", passwordEncoder)).isTrue();
	}

	@Test
	void isActive() {
		assertThat(member.isActive()).isFalse();

		member.activate();

		assertThat(member.isActive()).isTrue();

		member.deactivate();

		assertThat(member.isActive()).isFalse();
	}

	@Test
	void invalidEmail() {
		assertThatThrownBy(() ->
				Member.register(createMemberRegisterRequest("invalid email"), passwordEncoder)
		).isInstanceOf(IllegalArgumentException.class);

		Member.register(new MemberRegisterRequest("jiwoon@email.com", "Jiwoon", "secret"), passwordEncoder);
	}

}