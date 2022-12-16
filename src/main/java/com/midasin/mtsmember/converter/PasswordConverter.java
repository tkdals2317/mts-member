package com.midasin.mtsmember.converter;

import com.midasin.mtsmember.utils.PasswordEncryptUtil;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

    private static final String BCRYPT = "{bcrypt}";

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (!StringUtils.hasText(attribute) || attribute.contains(BCRYPT)) {
            return attribute;
        }
        return PasswordEncryptUtil.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
