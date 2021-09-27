package com.livenow.swarmloginservice.user.ui;

import com.livenow.swarmloginservice.user.application.UserService;
import com.livenow.swarmloginservice.common.exception.DuplicateException;
import com.livenow.swarmloginservice.common.exception.NotFoundException;
import com.livenow.swarmloginservice.user.ui.dto.UserRequest;
import com.livenow.swarmloginservice.user.ui.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        if (Objects.nonNull(session.getAttribute("user"))) {
            return "redirect:/index.html";
        }
        log.info("Loggin Page");
        return "/login.html";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam String account, @RequestParam String password, HttpSession session) {
        log.info("Loggin Request");
        if (Objects.nonNull(session.getAttribute("user"))) {
            return "redirect:/index.html";
        }
        final UserResponse userResponse = userService.userLogin(new UserRequest(account, password));
        session.setAttribute("user", userResponse);
        return "redirect:/index.html";
    }

    @GetMapping("/register")
    public String registerPage() {
        log.info("Register Page");
        return "/register.html";
    }

    @PostMapping("/register")
    public String userRegister(@RequestParam String account, @RequestParam String password, @RequestParam String email) {
        log.info("Register Request");
        final UserRequest request = new UserRequest(account, password, email);
        userService.save(request);
        return "redirect:/login.html";
    }

    @ExceptionHandler(DuplicateException.class)
    public String runtimeException(DuplicateException duplicateException) {
        log.info("DuplicateException: {}", duplicateException.getMessage());
        return "redirect:/401.html";
    }

    @ExceptionHandler(NotFoundException.class)
    public String runtimeException(NotFoundException notFoundException) {
        log.info("NotFoundException: {}", notFoundException.getMessage());
        return "redirect:/401.html";
    }

    @ExceptionHandler(Exception.class)
    public String runtimeException(Exception exception) {
        log.info("Internal Server Error: {}", exception.getMessage());
        return "redirect:/500.html";
    }
}
