package kafka.boardproject.repository;

import kafka.boardproject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board , Integer> {
    List<Board> findAllByOrderByCreatedAtDesc();

}
