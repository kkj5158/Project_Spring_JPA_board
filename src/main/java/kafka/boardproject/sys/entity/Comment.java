package kafka.boardproject.sys.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import kafka.boardproject.sys.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int ID;

    private String content;

    @ManyToOne
    @JoinColumn(name = "User_username", insertable = false, updatable = false)
    private User user;

    @Column(name = "User_username")
    private String username;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Board_boardid", insertable = false, updatable = false)
    private Board board;

    @Column(name = "Board_boardid")
    private int boardID;

    @JsonManagedReference
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentLike> commentLikes = new ArrayList<>();



    public Comment(CommentDto commentDto , String username) {
        this.ID = commentDto.getID();
        this.content = commentDto.getContent();
        this.boardID = commentDto.getBoard_ID();

        this.username = username;
    }

    public void update(CommentDto commentDto) {
        this.content = commentDto.getContent();
    }
}
