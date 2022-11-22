package tanulmanyitervezo.tervezo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.model.GradeHomework;

import java.util.List;

public interface GradeHomeworkRepository extends JpaRepository<GradeHomework, Integer> {
    List<GradeHomework> findAllByUser_id(int user_id);
}
