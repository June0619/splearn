package me.jwjung.splearn.application.provided;

import me.jwjung.splearn.domain.Member;
import me.jwjung.splearn.domain.MemberRegisterRequest;

/**
 * 회원의 등록과 관련된 기능을 제공한다
 */
public interface MemberRegister {
    Member register(MemberRegisterRequest memberRegisterRequest);
}
