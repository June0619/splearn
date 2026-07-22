package me.jwjung.splearn.application.required;

import me.jwjung.splearn.domain.Member;
import org.springframework.data.repository.Repository;

public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);
}
