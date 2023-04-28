package kafka.boardproject.sys.dto;

import kafka.boardproject.sys.entity.Board;
import kafka.boardproject.sys.entity.BoardLike;
import kafka.boardproject.sys.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardDto {

    private int ID;

    private String title;

    private String content;


    int boardLikes;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private List<CommentDto> comments;


    public BoardDto(Board board) {
        this.ID = board.getID();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        if(board.getComments() == null) {
            this.comments = null;
        }
        else{
            this.comments = board.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
        }

        this.boardLikes = board.getBoardLikes().size();
    }
}
