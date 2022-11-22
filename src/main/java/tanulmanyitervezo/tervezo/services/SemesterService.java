package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Semester;
import tanulmanyitervezo.tervezo.repository.SemesterRepository;

import java.util.Collections;
import java.util.List;

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
    public Semester setCurrent(int id){
        Semester semester = findCurrent();
        if(semester!=null){
            semester.setCurrent(false);
            repository.save(semester);
        }
        Semester newCurrent = repository.findById(id).get();
        newCurrent.setCurrent(true);
        repository.save(newCurrent);
        return  newCurrent;
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
    public Semester findById(int id){
        Semester semester = repository.findById(id).get();
        return semester;
    }

    public Semester update(int id, Semester semester){
        Semester s = repository.findById(id).get();
        if(semester.getName()!=null && !semester.getName().equals("")) s.setName(semester.getName());
        if(semester.getEnd()!=null) s.setEnd(semester.getEnd());
        if(semester.getStart()!=null) s.setStart(semester.getStart());
        return repository.save(s);
    }
}
