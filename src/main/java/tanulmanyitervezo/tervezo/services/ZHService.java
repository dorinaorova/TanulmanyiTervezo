package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Subject;
import tanulmanyitervezo.tervezo.model.ZH;
import tanulmanyitervezo.tervezo.repository.ZHRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZHService  {

    @Autowired
    private ZHRepository repository;

    @Autowired
    private StudentsSubjectService studentsSubjectService;

    public ZH addZH(ZH zh){
        return repository.save(zh);
    }

    public List<ZH> findBySubjectId(long id){
        return repository.findBySubject_Id(id);
    }

    public List<ZH> findByUserId(int userId, int semesterId){
        List<Subject> subjects = studentsSubjectService.findSubjectsByStudent_id(userId, semesterId);
        List<ZH> zhList = new ArrayList<>();
        if(subjects!=null){
            for(Subject s: subjects){
                List<ZH> zhForSubject= findBySubjectId(s.getId());
                zhList.addAll(zhForSubject);
            }
        }
        return zhList;
    }

    public void deleteZH(int id){
        repository.deleteById(id);
    }

    public void deleteAllBySubject_id(long id){
        repository.deleteBySubject_Id(id);
    }
}
