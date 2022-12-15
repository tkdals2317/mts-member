package com.midasin.mtsmember.domain.member;

import com.midasin.mtsmember.domain.member.rqrs.MemberDto;
import com.midasin.mtsmember.domain.role.Role;
import com.midasin.mtsmember.domain.role.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberDto createMember(MemberDto memberDto) {
        Assert.notNull(memberDto, "적합하지 않은 파라미터");

        // 중복 체크(인증 서버로 하면 Redis에서 조회하는 로직으로 변경
        validateDuplicateEmail(memberDto.getEmail());

        final Member savedMember = memberRepository.save(memberDto.toEntity());
        final Role role = new Role(RoleType.ROLE_USER);
        savedMember.grantRoles(role);

        return MemberDto.from(savedMember);
    }

    private void validateDuplicateEmail(String email) {
        Assert.notNull(email, "적합하지 않은 파라미터");

        Optional<Member> findMember = memberRepository.findByEmail(email);

        if (findMember.isPresent()) {
            throw new RuntimeException("이미 가입되어 있는 아이디 입니다.");
        }
    }

    /**
     * 이메일 중복 확인
     * @param email 이메일
     * @return 이메일 중복 여부
     */
    public boolean checkEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }

    /**
     * 닉네임 중복 확인
     * @param nickName 닉네임
     * @return 닉네임 중복 여부
     */
    public boolean checkNickNameDuplicate(String nickName) {
        return memberRepository.existsByNickName(nickName);
    }
}
