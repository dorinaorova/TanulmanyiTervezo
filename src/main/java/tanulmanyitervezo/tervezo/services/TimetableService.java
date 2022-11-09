package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.Period;
import tanulmanyitervezo.tervezo.Models.Subject;
import tanulmanyitervezo.tervezo.Models.TimetableResponseModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimetableService {

    @Autowired
    PeriodService periodService;

    @Autowired
    StudentsSubjectService subjectService;

    public List<TimetableResponseModel> findAllForDay(int studentId, String day){
        ArrayList<TimetableResponseModel> timeTable = new ArrayList<>();
        List<Subject> subjects =  subjectService.findCurrentByStudent_id(studentId);
        for(Subject s: subjects){
            List<Period> periods = periodService.findBySubjectId(s.getId());
            for(Period p: periods) {
                if (p.getDay().equals(day)) {
                    TimetableResponseModel timetableResponseModel = new TimetableResponseModel(
                            s.getName(),
                            p
                    );
                    timeTable.add(timetableResponseModel);
                }
            }
        }
        return timeTable;
    }

}
