package com.midasin.mtsmember.infra;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;

public interface BaseController {

    default UserDetails getMemberDto(@NotNull Authentication authentication) {
        return (UserDetails) authentication.getPrincipal();
    }
}
