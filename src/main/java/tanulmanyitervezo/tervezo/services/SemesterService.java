package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Semester;
import tanulmanyitervezo.tervezo.repository.SemesterRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    @Autowired
    SemesterRepository repository;

    public Semester addSemester(Semester semester){
        if(semester.isCurrent()){
            Semester current = findCurrent();
            if(current!=null) current.setCurrent(false);
        }
        return repository.save(semester);
    }

    public List<Semester> findAll(){
        return repository.findAll();
    }

    public Semester findCurrent(){
        List<Semester> semesters = repository.findAll();
        for (Semester s : semesters){
            if(s.isCurrent()) return s;
        }
        return null;
    }
    public Semester setCurrent(int id) throws Exception {
        Semester semester = findCurrent();
        if(semester!=null){
            semester.setCurrent(false);
            repository.save(semester);
        }

        Optional<Semester> newCurrent = repository.findById(id);
        if(newCurrent.isPresent()) {
            newCurrent.get().setCurrent(true);
            return repository.save(newCurrent.get());
        }
        else throw new Exception("NOT FOUND");
    }

    public Semester findNext(int id){
        List<Semester> semesters = repository.findAll();
        Collections.sort(semesters);
        for(int i=0; i< semesters.size(); i++){
            if(semesters.get(i).getId()==id){
                if(i< semesters.size()-1) {
                    i++;
                    return semesters.get(i);
                }
            }
        }
        return null;
    }

    public Semester findPrevious(int id){
        List<Semester> semesters = repository.findAll();
        Collections.sort(semesters);
        for(int i=0; i< semesters.size(); i++){
            if(semesters.get(i).getId()==id){
                if(i>0) {
                    i--;
                    return semesters.get(i);
                }
            }
        }
        return null;
    }
    public Optional<Semester> findById(int id){
        Optional<Semester> semester = repository.findById(id);
        return semester;
    }

    public Semester update(int id, Semester semester) throws Exception {
        Optional<Semester> s = repository.findById(id);
        if(s.isPresent()) {
            if (semester.getName() != null && !semester.getName().equals("")) s.get().setName(semester.getName());
            if (semester.getEnd() != null) s.get().setEnd(semester.getEnd());
            if (semester.getStart() != null) s.get().setStart(semester.getStart());
            return repository.save(s.get());
        }
        else throw new Exception("NOT FOUND");
    }
}
