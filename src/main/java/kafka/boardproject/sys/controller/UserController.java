package kafka.boardproject.sys.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kafka.boardproject.sys.dto.http.DefaultRes;
import kafka.boardproject.sys.dto.LoginRequsetDto;
import kafka.boardproject.sys.dto.SignupRequsetDto;
import kafka.boardproject.sys.dto.http.ResponseMessage;
import kafka.boardproject.sys.dto.http.StatusCode;
import kafka.boardproject.sys.service.UserService;
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
    public ResponseEntity signup(@Valid SignupRequsetDto signupRequestDto) {
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
