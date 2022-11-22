package tanulmanyitervezo.tervezo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.model.Period;

import java.util.List;

public interface PeriodRepository  extends JpaRepository<Period, Integer> {
    public List<Period> findBySubject_Id(long subject_id);

    public void deleteBySubject_Id(long subject_id);
}
