package kafka.boardproject.sys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import kafka.boardproject.sys.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequsetDto {

    @NotBlank(message = "아이디는 필수 입력입니다.")
    @Size(min = 4, max = 10, message = "아이디는 4자이상 10자 이하를 입력하셔야 합니다.")
    @Pattern(regexp ="^[a-z0-9]*$", message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.")
    private String username;
    @NotBlank(message = "비밀번호는 필수 입력입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자이상 15자 이하를 입력하셔야 합니다.")
    @Pattern(regexp ="^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,15}$", message = "비밀번호는 알파벳  대소문자(a~z, A~Z), 숫자(0~9), 특수문자로 구성되어야 합니다.")
    private String password;

    private UserRoleEnum role;
    private boolean admin = false;
    private String adminToken = "";
}
