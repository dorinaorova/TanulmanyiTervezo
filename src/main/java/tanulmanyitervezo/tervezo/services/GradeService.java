package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.*;
import tanulmanyitervezo.tervezo.model.ResponseModels.GradeResponseModel;
import tanulmanyitervezo.tervezo.repository.GradeHomeworkRepository;
import tanulmanyitervezo.tervezo.repository.GradeZHRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    GradeZHRepository gradeZHRepository;

    @Autowired
    GradeHomeworkRepository gradeHomeworkRepository;

    @Autowired
    UserService userService;

    @Autowired
    ZHService zhService;

    @Autowired
    HomeworkService homeworkService;

    @Autowired
    StudentsSubjectService subjectService;

    private List<GradeZH> findAllGradesByUser_id_ZH(int id){
        return gradeZHRepository.findAllByUser_id(id);
    }

    private List<GradeHomework> findAllGradesByUser_id_Homework(int id){
        return gradeHomeworkRepository.findAllByUser_id(id);
    }

    private GradeZH findByZh_id(int userId, int zhId){
        List<GradeZH> grades = findAllGradesByUser_id_ZH(userId);
        for(GradeZH g: grades){
            if(g.getZh().getId()==zhId) return g;
        }
        return null;
    }

    private GradeHomework findByHomework_id(int userId, int zhId){
        List<GradeHomework> grades = findAllGradesByUser_id_Homework(userId);
        for(GradeHomework g: grades){
            if(g.getHomework().getId()==zhId) return g;
        }
        return null;
    }

    public GradeZH addGrade_ZH(GradeZH grade, int userId) throws Exception {
        Optional<User> user =  userService.findById(userId);
        if(user.isPresent()) {
            grade.setUser(user.get());
            return gradeZHRepository.save(grade);
        }
        else throw new Exception("NOT FOUND");
    }

    public GradeZH updateGrade_ZH(GradeZH g, int id) throws Exception {
        Optional<GradeZH> grade = gradeZHRepository.findById(id);
        if(grade.isPresent()) {
            grade.get().setPoints(g.getPoints());
            return gradeZHRepository.save(grade.get());
        }
        else throw new Exception("NOT FOUND");
    }

    public GradeHomework addGrade_Homework(GradeHomework grade, int userId) throws Exception {
        Optional<User> user =  userService.findById(userId);
        if(user.isPresent()) {
            grade.setUser(user.get());
            return gradeHomeworkRepository.save(grade);
        }
        else throw new Exception("NOT FOUND");
    }

    public GradeHomework updateGrade_Homework(GradeHomework g, int id) throws Exception {
        Optional<GradeHomework> grade = gradeHomeworkRepository.findById(id);
        if(grade.isPresent()) {
            grade.get().setPoints(g.getPoints());
            return gradeHomeworkRepository.save(grade.get());
        }
        else throw new Exception("NOT FOUND");
    }

    public List<GradeResponseModel> findAllByUser_id(int userId, int semesterId){
        List<GradeResponseModel> grades = new ArrayList<>();
        List<Subject> subjects = subjectService.findSubjectsByStudent_id(userId, semesterId);
        if (subjects != null) {
            for(Subject s: subjects){
                GradeResponseModel gradeResponseModel = new GradeResponseModel(s.getName(), s.getId());
                List<ZH> zhList = zhService.findBySubjectId(s.getId());
                if(zhList!=null){
                    for(ZH zh: zhList){
                        GradeZH grade= findByZh_id(userId, zh.getId());
                        if(grade!= null){
                            gradeResponseModel.addGradeZH(grade);
                        }
                        else{
                            gradeResponseModel.addZHWithoutPoints(zh);
                        }
                    }
                }

                List<Homework> homeworks = homeworkService.findBySubjectId(s.getId());
                if(homeworks != null){
                    for(Homework hw: homeworks){
                        GradeHomework grade = findByHomework_id(userId, hw.getId());
                        if(grade != null){
                            gradeResponseModel.addGradeHomework(grade);
                        }
                        else{
                            gradeResponseModel.addHomeworkWithoutPoints(hw);
                        }
                    }
                }

                if(gradeResponseModel.getGradeZHList().size()>0 || gradeResponseModel.getGradeHomeworks().size()>0){
                    grades.add(gradeResponseModel);
                }
            }
        }
        return grades;
    }
}
