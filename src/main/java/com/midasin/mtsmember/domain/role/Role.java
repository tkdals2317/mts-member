package com.midasin.mtsmember.domain.role;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.midasin.mtsmember.domain.BaseEntity;
import com.midasin.mtsmember.domain.member.Member;
import com.midasin.mtsmember.domain.role.enums.RoleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 권한 Entity
 */
@Table(
        name = "role",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_member_role_type_001",
                        columnNames = {"member_id", "role_type"}
                )
        })
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role extends BaseEntity {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "fk_role_member_001"))
    private Member member;

    @Column(name = "role_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }

    public void grant(Member member) {
        this.member = member;
    }

}
