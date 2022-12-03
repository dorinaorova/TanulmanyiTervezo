package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Period;
import tanulmanyitervezo.tervezo.model.Subject;
import tanulmanyitervezo.tervezo.model.ResponseModels.TimetableResponseModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimetableService {

    @Autowired
    PeriodService periodService;

    @Autowired
    StudentsSubjectService subjectService;

    public List<TimetableResponseModel> findAllForDay(int studentId, int day){
        ArrayList<TimetableResponseModel> timeTable = new ArrayList<>();
        List<Subject> subjects =  subjectService.findCurrentByStudent_id(studentId);
        if(subjects!=null) {
            for (Subject s : subjects) {
                List<Period> periods = periodService.findBySubjectId(s.getId());
                for (Period p : periods) {
                    if (p.getDay()==day) {
                        TimetableResponseModel timetableResponseModel = new TimetableResponseModel(
                                s.getName(),
                                p
                        );
                        timeTable.add(timetableResponseModel);
                    }
                }
            }
        }
        return timeTable;
    }

}
