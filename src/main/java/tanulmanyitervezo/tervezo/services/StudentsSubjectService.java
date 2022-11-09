package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.Semester;
import tanulmanyitervezo.tervezo.Models.StudentsSubject;
import tanulmanyitervezo.tervezo.Models.Subject;
import tanulmanyitervezo.tervezo.Models.User;
import tanulmanyitervezo.tervezo.Repository.StudentsSubjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsSubjectService {

    @Autowired
    StudentsSubjectRepository repository;

    @Autowired
    UserService userService;

    @Autowired
    SemesterService semesterService;

    public List<StudentsSubject> findAll(){
        return  repository.findAll();
    }

    public List<Subject> findByStudentId(int id){
        List<StudentsSubject> studentsSubjects = repository.findAllByStudent_Id(id);
        ArrayList<Subject> subjects = new ArrayList<>();
        for(StudentsSubject s: studentsSubjects){
            subjects.addAll(s.getSubject());
        }
        return subjects;
    }
    public StudentsSubject add(Subject subject,int id){
        boolean findSS = false;
        StudentsSubject newStudentSubject = new StudentsSubject();
        List<StudentsSubject> studentsSubjects = repository.findAllByStudent_Id(id);
        for(StudentsSubject ss: studentsSubjects){
            if(ss.getSemester().isCurrent()){
                boolean subjectExist=false;
                for(Subject s: ss.getSubject()){
                    if(s.getId()==subject.getId()){
                        subjectExist=true;
                    }
                }
                if(!subjectExist) {
                    ss.addSubject(subject);
                }
                newStudentSubject=ss;
                findSS = true;
                repository.save(newStudentSubject);
            }
        }
        if(!findSS){
            User student = userService.findById(id).get();
            Semester semester = semesterService.findCurrent();
            newStudentSubject.setSemester(semester);
            newStudentSubject.setStudent(student);
            newStudentSubject.addSubject(subject);
            repository.save(newStudentSubject);
        }
        return newStudentSubject;
    }

    public List<Subject> findSubjectsByStudent_id(int id, int semesterid){
        List<StudentsSubject> studentsSubjects = repository.findAllByStudent_Id(id);
        for(StudentsSubject ss: studentsSubjects){
            if(ss.getSemester().getId()==semesterid){
                return ss.getSubject();
            }
        }
        return null;
    }

    public List<Subject> findCurrentByStudent_id(int id){
        Semester current = semesterService.findCurrent();
        List<Subject> subjects = findSubjectsByStudent_id(id, current.getId());
        return  subjects;
    }
}
