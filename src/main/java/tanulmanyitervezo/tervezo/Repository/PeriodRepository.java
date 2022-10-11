package tanulmanyitervezo.tervezo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.Models.Period;

import java.util.List;

public interface PeriodRepository  extends JpaRepository<Period, Integer> {
    public List<Period> findBySubject_Id(long subject_id);
}
