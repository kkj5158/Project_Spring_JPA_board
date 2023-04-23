package kafka.boardproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginRequsetDto {
    private String username;
    private String password;
}
