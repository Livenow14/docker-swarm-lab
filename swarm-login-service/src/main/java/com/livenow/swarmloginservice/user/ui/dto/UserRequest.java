package com.livenow.swarmloginservice.user.ui.dto;

import com.livenow.swarmloginservice.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRequest {

    private String account;
    private String password;
    private String email;

    public UserRequest(String account, String password) {
        this(account, password, null);
    }

    public UserRequest(String account, String password, String email) {
        this.account = account;
        this.password = password;
        this.email = email;
    }

    public User toEntity() {
        return User.builder()
                .account(account)
                .password(password)
                .email(email)
                .build();
    }
}
