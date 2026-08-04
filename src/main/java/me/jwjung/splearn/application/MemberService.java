package me.jwjung.splearn.application;

import lombok.RequiredArgsConstructor;
import me.jwjung.splearn.application.provided.MemberRegister;
import me.jwjung.splearn.application.required.EmailSender;
import me.jwjung.splearn.application.required.MemberRepository;
import me.jwjung.splearn.domain.Member;
import me.jwjung.splearn.domain.MemberRegisterRequest;
import me.jwjung.splearn.domain.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberRegister {
    private final MemberRepository memberRepository;
    private final EmailSender emailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member register(MemberRegisterRequest registerRequest) {
        Member member = Member.register(registerRequest, passwordEncoder);

        memberRepository.save(member);

        emailSender.send(member.getEmail(), "등록을 완료해주세요", "아래 링크를 클릭해서 등록을 완료해주세요");
        return member;
    }
}
