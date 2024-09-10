package com.study.jpa.data.entity;


import com.study.jpa.common.Type;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "Member")
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)})
public class Member {

    @Id
    @Column(name="ID")
    private String id;

    @Column(name="NAME", nullable = false, length = 10)
    private String username;

    @Column(name="AGE")
    private Integer age;

    // 추가
    @Column(name="role_type")
    @Enumerated(EnumType.STRING)
    private Type.RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name="last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;
}
