package com.livenow.swarmloginservice.user.application;

import com.livenow.swarmloginservice.common.exception.DuplicateException;
import com.livenow.swarmloginservice.common.exception.NotFoundException;
import com.livenow.swarmloginservice.user.domain.User;
import com.livenow.swarmloginservice.user.domain.UserRepository;
import com.livenow.swarmloginservice.user.ui.dto.UserRequest;
import com.livenow.swarmloginservice.user.ui.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Transactional
    public void save(UserRequest userRequest) {
        validateExist(userRequest);
        userRepository.save(userRequest.toEntity());
    }

    public UserResponse userLogin(UserRequest userRequest) {
        final Optional<User> foundUser = findByUserByAccount(userRequest.getAccount());
        final User user = foundUser.stream()
                .filter(it -> it.isSamePassword(userRequest.getPassword()))
                .findAny()
                .orElseThrow(() -> new NotFoundException("찾을 수 없는 아이디, 비밀번호입니다."));
        return new UserResponse(user);
    }

    private Optional<User> findByUserByAccount(String account) {
       return userRepository.findByAccount(account);
    }

    private void validateExist(UserRequest userRequest) {
        if (userRepository.existsByAccount(userRequest.getAccount())) {
            log.info("이미 존재하는 Account 입니당 입력 값: {}", userRequest.getAccount());
            throw new DuplicateException("이미 존재하는 Account 입니당 입력 값: " + userRequest.getAccount());
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            log.info("이미 존재하는 email입니당 입력 값: {}", userRequest.getEmail());
            throw new DuplicateException("이미 존재하는 email입니당 입력 값: " + userRequest.getEmail());
        }
    }
}
