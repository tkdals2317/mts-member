package com.midasin.mtsmember.domain.member;

import com.midasin.mtsmember.domain.member.rqrs.MemberDto;
import com.midasin.mtsmember.domain.role.Role;
import com.midasin.mtsmember.domain.role.enums.RoleType;
import com.midasin.mtsmember.infra.CustomException;
import com.midasin.mtsmember.infra.ErrorMessage;
import com.midasin.mtsmember.utils.PasswordEncryptUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberDto createMember(MemberDto memberDto) {
        Assert.notNull(memberDto, ErrorMessage.INVALID_PARAM.name());

        if (checkEmailDuplicate(memberDto.getEmail())) {
            throw new CustomException(ErrorMessage.EXIST_EMAIL);
        }

        final Member savedMember = memberRepository.save(memberDto.toEntity());

        final Role role = new Role(RoleType.ROLE_USER);
        savedMember.grantRoles(role);

        return MemberDto.from(savedMember);
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

    /**
     * 로그인 인증 토큰 발급 시 사용자 조회
     * @param email
     * @param password
     * @return
     */
    public MemberDto findAuthMember(String email, String password) {
        Assert.notNull(email, ErrorMessage.INVALID_PARAM.name());
        Assert.notNull(password, ErrorMessage.INVALID_PARAM.name());

        final Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorMessage.NOT_EXIST_MEMBER));

        final String savedPassword = member.getPassword();

        if (!PasswordEncryptUtil.match(savedPassword, password)) {
            throw new CustomException(ErrorMessage.INVALID_PASSWORD);
        }

        return MemberDto.from(member);
    }
}
