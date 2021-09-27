package com.livenow.swarmloginservice.user.application;

import com.livenow.swarmloginservice.user.domain.User;
import com.livenow.swarmloginservice.user.domain.UserRepository;
import com.livenow.swarmloginservice.user.ui.dto.UserRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("사용자 비지니스 흐름 테스트")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveAndFind() {
        //given
        final UserRequest request = new UserRequest("gump", "1q2w3e4r", "gump@gump.com");
        //when
        userService.save(request);
        //then
        final Optional<User> foundUser = userRepository.findByAccount(request.getAccount());
        Assertions.assertThat(foundUser).isNotEmpty();
    }
}
