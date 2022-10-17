package tanulmanyitervezo.tervezo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.Models.Homework;

import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
    public List<Homework> findBySubject_Id(long subject_id);
    public void deleteBySubject_Id(long subject_id);
}
