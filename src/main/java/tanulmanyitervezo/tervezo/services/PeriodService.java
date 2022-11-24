package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Period;
import tanulmanyitervezo.tervezo.model.Subject;
import tanulmanyitervezo.tervezo.repository.PeriodRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodService {

    @Autowired
    PeriodRepository repository;

    @Autowired
    SubjectService subjectService;

    public Period addPeriod(Period period, long subjectId) throws Exception {
        Optional<Subject> subject = subjectService.findById(subjectId);
        if(subject.isPresent())
        {
            period.setSubject(subject.get());
            return repository.save(period);
        }
        else throw new Exception("NOT FOUND");
    }

    public List<Period> findBySubjectId(long id){
        return repository.findBySubject_Id(id);
    }

    public void deletePeriod(int id) throws Exception {
        try {
            repository.deleteById(id);
        }
        catch(Exception e){
            throw new Exception("NOT FOUND");
        }
    }
}
