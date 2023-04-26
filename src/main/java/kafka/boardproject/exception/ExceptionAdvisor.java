package kafka.boardproject.exception;

import kafka.boardproject.sys.dto.http.DefaultRes;
import kafka.boardproject.sys.dto.http.ResponseMessage;
import kafka.boardproject.sys.dto.http.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

// 아이디와 비밀번호를 검증해주는 클래스
// 검증 오류 발생시에 메세지를 간략히 빌딩해서 반환해준다.
@ControllerAdvice   // 전역 설정을 위한 annotaion
@RestController
public class ExceptionAdvisor {

    //회원가입시에 아이디/비밀번호 검증 오류
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity processValidationError(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("] \n");
        }


        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.CREATED_USER_FAIL,  builder.toString()), HttpStatus.BAD_REQUEST);



    }

    // 로그인/검증 토큰 오류

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity processLoginError(IllegalArgumentException exception) {
        String errorMsg = exception.getMessage();

        System.out.println(errorMsg);

        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, errorMsg), HttpStatus.BAD_REQUEST);



    }



}