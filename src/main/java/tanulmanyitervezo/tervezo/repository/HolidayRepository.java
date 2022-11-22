package tanulmanyitervezo.tervezo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tanulmanyitervezo.tervezo.model.Holiday;

import java.util.Date;
import java.util.List;

public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    public List<Holiday> findAllByDate(Date date);
}
