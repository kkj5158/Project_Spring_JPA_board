package kafka.boardproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
@NoArgsConstructor
@Entity(name="users")
public class User {
    @Id
    @Size(min = 4, max = 10)
    @Pattern(regexp ="^[a-z0-9]*$", message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)로 구성되어야 합니다.")
    private String username;

    @Column(nullable = false)
    @Size(min = 8, max = 15)
    @Pattern(regexp ="^[a-zA-Z0-9]*$", message = "최소 4자 이상, 10자 이하이며 알파벳  대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 합니다.")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
