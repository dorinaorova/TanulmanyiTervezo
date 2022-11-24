package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Subject;
import tanulmanyitervezo.tervezo.model.ZH;
import tanulmanyitervezo.tervezo.repository.ZHRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ZHService  {

    @Autowired
    private ZHRepository repository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentsSubjectService studentsSubjectService;

    public ZH addZH(ZH zh, long subjectId) throws Exception {
        Optional<Subject> subject = subjectService.findById(subjectId);
        if(subject.isPresent()){
            zh.setSubject(subject.get());
            return repository.save(zh);
        }
        else throw new Exception("NOT FOUND");
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

    public void deleteZH(int id) throws Exception {
        try {
            repository.deleteById(id);
        }catch (Exception e){
            throw new Exception("NOT FOUND");
        }
    }

}
