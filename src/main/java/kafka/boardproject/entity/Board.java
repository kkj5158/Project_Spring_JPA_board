package kafka.boardproject.entity;

import jakarta.persistence.*;
import kafka.boardproject.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int ID;

    private String title;

    private String content;

    private String author;

    private String pw;

    public Board(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.author = boardDto.getAuthor();
        this.pw = boardDto.getPw();
    }

    public void update(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.author = boardDto.getAuthor();
        this.pw = boardDto.getPw();
    }
}