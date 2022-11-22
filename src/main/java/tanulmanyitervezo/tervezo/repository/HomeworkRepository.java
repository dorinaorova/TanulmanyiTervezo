package tanulmanyitervezo.tervezo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.model.Homework;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
    public List<Homework> findBySubject_Id(long subject_id);
    public void deleteBySubject_Id(long subject_id);
}
