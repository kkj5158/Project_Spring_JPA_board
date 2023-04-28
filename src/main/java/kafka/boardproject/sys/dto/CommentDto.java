package kafka.boardproject.sys.dto;

import kafka.boardproject.sys.entity.Comment;
import kafka.boardproject.sys.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentDto {

    private int ID;
    private int board_ID;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private int commentLikes;

    public CommentDto(Comment comment) {
        this.ID = comment.getID();
        this.board_ID=comment.getBoardID();
        this.username = comment.getUsername();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.commentLikes = comment.getCommentLikes().size();
    }
}
