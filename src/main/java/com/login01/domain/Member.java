package com.login01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Member {

    @Id
    private Long id;

    private String email;
    private String password;
    private String role;
}
