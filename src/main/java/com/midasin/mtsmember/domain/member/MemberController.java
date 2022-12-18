package com.midasin.mtsmember.domain.member;

import com.midasin.mtsmember.domain.member.rqrs.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "회원 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class MemberController {

    private final MemberService memberService;
    
    @Operation(summary = "회원 가입")
    @PostMapping(value = "/signup")
    public MemberDto createMember(@RequestBody MemberDto memberDto) {
        return memberService.createMember(memberDto);
    }
    
    @Operation(summary = "이메일 중복 확인")
    @GetMapping(value = "/email/{email}/exists")
    public boolean checkEmailDuplicate(@PathVariable String email) {
        return memberService.checkEmailDuplicate(email);
    }
    
    @Operation(summary = "닉네임 중복 확인")
    @GetMapping(value = "/nickname/{nickname}/exists")
    public boolean checkNickNameDuplicate(@PathVariable String nickname) {
        return memberService.checkNickNameDuplicate(nickname);
    }

}
