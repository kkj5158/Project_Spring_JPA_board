package kafka.boardproject.sys.dto;

import kafka.boardproject.sys.entity.Board;
import kafka.boardproject.sys.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardDto {

    private int ID;

    private String title;

    private String content;

    private List<Comment> comments;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public BoardDto(Board board) {
        this.ID = board.getID();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.comments = board.getComments();
    }
}
