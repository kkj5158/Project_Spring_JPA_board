package kafka.boardproject.sys.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import kafka.boardproject.sys.dto.BoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int ID;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "User_username", insertable = false, updatable = false)
    private User user;

    @Column(name = "User_username")
    private String username;
    @JsonManagedReference
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("createdAt desc") // 댓글 정렬
    private List<Comment> comments;

    public Board(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }

    public Board(BoardDto boardDto, String username) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.username = username;

    }

    public void update(BoardDto boardDto) {
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }
}
