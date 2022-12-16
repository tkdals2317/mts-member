create table `mts-db`.member
(
    member_id               bigint auto_increment primary key             comment '사용자 ID',
    email                   varchar(350)                         not null comment '이메일',
    member_name             varchar(40)                          not null comment '이름',
    nick_name               varchar(40)                          not null comment '닉네임',
    password                varchar(100)                         not null comment '비밀번호',
    phone                   varchar(100)                         not null comment '전화번호' ,
    department              varchar(100)                             null comment '부서',
    create_date_time        datetime default current_timestamp() not null comment '생성 일자',
    last_modified_date_time datetime default current_timestamp() not null comment '수정 일자',
    constraint uk_member_001 unique (email),
    constraint uk_member_002 unique (phone)
) comment '사용자' CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;


create table `mts-db`.role
(
    role_id                 bigint auto_increment  primary key            comment '권한 ID',
    role_type               varchar(255)                         not null comment '권한 타입',
    member_id               bigint                               not null comment '사용자 ID',
    create_date_time        datetime default current_timestamp() not null comment '생성 일자',
    last_modified_date_time datetime default current_timestamp() not null comment '수정 일자',
    constraint uk_member_role_type_001 unique (member_id, role_type),
    constraint fk_role_member_001 foreign key (member_id) references `mts-db`.member (member_id)
) comment '권한' CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;