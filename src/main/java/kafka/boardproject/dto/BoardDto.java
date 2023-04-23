package kafka.boardproject.dto;

import jakarta.persistence.*;
import kafka.boardproject.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class BoardDto {

    private int ID;

    private String title;

    private String content;

    private String author;

    private String pw;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public BoardDto(Board board) {
        this.ID = board.getID();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.author = board.getPw();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}
