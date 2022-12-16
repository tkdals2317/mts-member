package com.midasin.mtsmember.utils;

import com.midasin.mtsmember.infra.ErrorMessage;
import lombok.experimental.UtilityClass;
import org.modelmapper.internal.util.Assert;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 비밀번호 암호화 유틸 클래스
 */
@UtilityClass
public class PasswordEncryptUtil {

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * 비밀번호 암호화
     * @param password 평문 비밀번호
     * @return 암호화된 비밀번호
     */
    public String encrypt(String password) {
        Assert.notNull(password, ErrorMessage.INVALID_PASSWORD.name());
        return passwordEncoder.encode(password);
    }

    /**
     * 비밀번호 일치 여부 조회
     * @param inputPassword 입력 비밀번호
     * @param encodePassword 인코딩된 비밀번호
     * @return 일치 여부
     */
    public boolean match(String inputPassword, String encodePassword) {
        Assert.notNull(inputPassword, ErrorMessage.INVALID_PASSWORD.name());
        Assert.notNull(encodePassword, ErrorMessage.INVALID_PASSWORD.name());

        return passwordEncoder.matches(inputPassword, encodePassword);
    }


}
