package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.Holiday;
import tanulmanyitervezo.tervezo.Models.Semester;
import tanulmanyitervezo.tervezo.Repository.HolidayRepository;
import tanulmanyitervezo.tervezo.Repository.SemesterRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HolidayService {
    @Autowired
    HolidayRepository repository;

    @Autowired
    SemesterRepository semesterRepository;

    public Holiday addHoliday(Holiday holiday){
        return repository.save(holiday);
    }

    public List<Holiday> findallHoliday(){
        return repository.findAll();
    }

    public List<Holiday> findByDate(int id){
        Semester semester = semesterRepository.findById(id).get();
        Date start = semester.getStart();
        Date end = semester.getEnd();
        List<Holiday> all = repository.findAll();
        ArrayList<Holiday> holidays = new ArrayList<>();
        for (Holiday h: all){
            if(betweenToDates(h.getDate(), start, end)) holidays.add(h);
            else if(h.isRepeating()){
                Calendar cal = Calendar.getInstance();
                cal.set(start.getYear(), h.getDate().getMonth(), h.getDate().getDay());
                Date holidayDate = cal.getTime();
                if(betweenToDates(holidayDate, start, end)) holidays.add(h);
                else{
                    cal.set(end.getYear(), h.getDate().getMonth(), h.getDate().getDay());
                    holidayDate = cal.getTime();
                    if(betweenToDates(holidayDate, start, end)) holidays.add(h);
                }
            }
        }
        return holidays;
    }

    public void deleteHoliday(int id){repository.deleteById(id);}

    private boolean betweenToDates(Date holiday, Date start, Date end){
        if(holiday.after(start) && holiday.before(end)) return true;
        else return false;
    }
}
