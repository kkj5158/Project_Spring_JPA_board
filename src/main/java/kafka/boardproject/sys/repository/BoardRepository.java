package kafka.boardproject.sys.repository;

import kafka.boardproject.sys.entity.Board;
import kafka.boardproject.sys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board , Integer> {
    List<Board> findAllByOrderByCreatedAtDesc();




}
