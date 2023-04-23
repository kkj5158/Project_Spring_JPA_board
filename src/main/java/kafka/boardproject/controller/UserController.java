package kafka.boardproject.controller;

import jakarta.servlet.http.HttpServletResponse;
import kafka.boardproject.dto.DefaultRes;
import kafka.boardproject.dto.LoginRequsetDto;
import kafka.boardproject.dto.SignupRequsetDto;
import kafka.boardproject.http.ResponseMessage;
import kafka.boardproject.http.StatusCode;
import kafka.boardproject.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(SignupRequsetDto signupRequestDto) {
        String status = userService.signup(signupRequestDto);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER), HttpStatus.OK);

    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity login(LoginRequsetDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS), HttpStatus.OK);
    }

}
