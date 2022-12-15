package com.midasin.mtsmember.domain.member;

import com.midasin.mtsmember.domain.member.rqrs.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/signup")
    public MemberDto createMember(@RequestBody MemberDto memberDto) {
        return memberService.createMember(memberDto);
    }

    @GetMapping(value = "/email/{email}/exists")
    public boolean checkEmailDuplicate(@PathVariable String email) {
        return memberService.checkEmailDuplicate(email);
    }

    @GetMapping(value = "/nickname/{nickname}/exists")
    public boolean checkNickNameDuplicate(@PathVariable String nickname) {
        return memberService.checkNickNameDuplicate(nickname);
    }

}
