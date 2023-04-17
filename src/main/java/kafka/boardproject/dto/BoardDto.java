package kafka.boardproject.dto;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
public class BoardDto {

    private int ID;

    private String title;

    private String content;

    private String author;

    private String pw;

    private Date createdAt;

    private Date modifiedAt;

}
