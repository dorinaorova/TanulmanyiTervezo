package tanulmanyitervezo.tervezo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.Models.ZH;

import java.util.List;

public interface ZHRepository extends JpaRepository<ZH, Integer> {
    public List<ZH> findBySubject_Id(long subject_id);

    public void deleteBySubject_Id(long subject_id);
}
