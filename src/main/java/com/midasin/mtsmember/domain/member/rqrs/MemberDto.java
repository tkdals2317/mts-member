package com.midasin.mtsmember.domain.member.rqrs;

import com.midasin.mtsmember.domain.member.Member;
import com.midasin.mtsmember.utils.ModelMapperUtil;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MemberDto {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String memberName;

    @NotNull
    private String nickName;

    private String phone;

    private String department;

    public static MemberDto from(Member member) {
        return ModelMapperUtil.map(member, MemberDto.class);
    }

    public Member toEntity() {
        return ModelMapperUtil.map(this, Member.class);
    }

}
