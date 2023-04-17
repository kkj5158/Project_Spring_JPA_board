package kafka.boardproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
public class Board {
    @Id
    private int id;

    private String title;

    private String content;

    private String author;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAtDATE;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAtDATE;


}
