package com.livenow.swarmloginservice.user.ui.dto;

import com.livenow.swarmloginservice.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

    private String account;
    private String email;

    public UserResponse(User user) {
        this(user.getAccount(), user.getEmail());
    }

    public UserResponse(String account, String email) {
        this.account = account;
        this.email = email;
    }
}
