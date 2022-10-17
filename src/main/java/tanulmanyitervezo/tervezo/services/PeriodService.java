package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.Period;
import tanulmanyitervezo.tervezo.Repository.PeriodRepository;

import java.util.List;

@Service
public class PeriodService {

    @Autowired
    PeriodRepository repository;

    public Period addPeriod(Period period){
        return repository.save(period);
    }

    public List<Period> findBySubjectId(long id){

        return repository.findBySubject_Id(id);
    }

    public void deleteAllBySubjectId(long id){
        repository.deleteBySubject_Id(id);
    }
}
