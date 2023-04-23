package kafka.boardproject.service;

import jakarta.servlet.http.HttpServletResponse;
import kafka.boardproject.dto.LoginRequsetDto;
import kafka.boardproject.dto.SignupRequsetDto;
import kafka.boardproject.entity.User;
import kafka.boardproject.jwt.JWTUtil;
import kafka.boardproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public String signup(SignupRequsetDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(username, password);

        userRepository.save(user);

        return "Suceess";

    }

    @Transactional(readOnly = true)
    public void login(LoginRequsetDto loginRequestDto, HttpServletResponse response) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JWTUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
    }
}
