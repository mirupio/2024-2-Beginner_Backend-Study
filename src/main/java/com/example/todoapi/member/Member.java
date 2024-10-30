package com.example.todoapi.member;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(name = "member_login_id", length = 20, nullable = false)
    private String loginId;

    @Column(name = "password", nullable = false)
    private String password;

    // Getter for id
    public Long getId() {
        return id;
    }

    // Getter for loginId
    public String getLoginId() {
        return loginId;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }
    // 필드 수정 메서드
    public void updateLoginId(String newLoginId) {
        this.loginId = newLoginId;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

}
