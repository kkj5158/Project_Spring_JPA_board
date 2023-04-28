package kafka.boardproject.sys.service;

import jakarta.servlet.http.HttpServletResponse;
import kafka.boardproject.sys.dto.LoginRequsetDto;
import kafka.boardproject.sys.dto.SignupRequsetDto;
import kafka.boardproject.sys.entity.User;
import kafka.boardproject.sec.jwt.JwtUtil;
import kafka.boardproject.sys.entity.UserRoleEnum;
import kafka.boardproject.sys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public void signup(SignupRequsetDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);

        userRepository.save(user);

    }

    @Transactional(readOnly = true)
    public void login(LoginRequsetDto loginRequestDto, HttpServletResponse response) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);

        if (found.isEmpty()) {
            throw new IllegalArgumentException("등록된 사용자가 없습니다.");
        }

        User user = found.get();


        // 비밀번호 확인
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        String token = jwtUtil.createToken(user.getUsername());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);


    }
}
