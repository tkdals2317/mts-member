package com.midasin.mtsmember.domain.member;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.midasin.mtsmember.domain.BaseEntity;
import com.midasin.mtsmember.domain.role.Role;
import com.midasin.mtsmember.converter.PasswordConverter;
import com.midasin.mtsmember.utils.RegexPattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;


/**
 * 멤버 Entity
 */
@Table(
        name = "member",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_member_001", columnNames = "email"),
                @UniqueConstraint(name = "uk_member_002", columnNames = "phone"),
        })
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

        @Id
        @Column(name = "member_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Pattern(regexp = RegexPattern.EMAIL_REGEX)
        @Column(nullable = false, length = 350)
        private String email;

        @Column(nullable = false, length = 100)
        @Convert(converter = PasswordConverter.class)
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;

        @Column(nullable = false, length = 40)
        private String memberName;

        @Column(nullable = false, length = 40)
        private String nickName;

        @Column(length = 100)
        @Pattern(regexp = RegexPattern.PHONE_REGEX)
        private String phone;

        @Column(length = 100)
        private String department;

        @JsonManagedReference
        @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
        private final List<Role> roles = new ArrayList<>();

        /**
         * 권한 부여
         *
         * @param role 권한
         */
        public void grantRoles(Role role) {
                this.roles.add(role);
                role.grant(this);
        }

}
