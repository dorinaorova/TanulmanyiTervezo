package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.Holiday;
import tanulmanyitervezo.tervezo.Models.Semester;
import tanulmanyitervezo.tervezo.Repository.HolidayRepository;
import tanulmanyitervezo.tervezo.Repository.SemesterRepository;

import java.util.*;

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
        List<Holiday> holidays =  repository.findAll();
        Collections.sort(holidays);
        return holidays;
    }

    public List<Holiday> findByDate(int id){
        Semester semester = semesterRepository.findById(id).get();
        Date start = semester.getStart();
        Date end = semester.getEnd();
        List<Holiday> all = repository.findAll();
        ArrayList<Holiday> holidays = new ArrayList<>();

        for (Holiday h: all){
            Date date = new Date(h.getDate());
            if(betweenToDates(date, start, end)) holidays.add(h);
            else if(h.isRepeating()){
                Calendar cal = Calendar.getInstance();
                cal.set(start.getYear(), date.getMonth(), date.getDay());
                Date holidayDate = cal.getTime();
                if(betweenToDates(holidayDate, start, end)) holidays.add(h);
                else{
                    cal.set(end.getYear(), date.getMonth(), date.getDay());
                    holidayDate = cal.getTime();
                    if(betweenToDates(holidayDate, start, end)) holidays.add(h);
                }
            }
        }
        Collections.sort(holidays);
        return holidays;
    }

    public void deleteHoliday(int id){repository.deleteById(id);}

    private boolean betweenToDates(Date holiday, Date start, Date end){
        if(holiday.after(start) && holiday.before(end)) return true;
        else return false;
    }

    public boolean isHoliday(long dateTs){
        List<Holiday> holidays = findallHoliday();
        Date date = new Date(dateTs);
        for(Holiday h: holidays){
            Date holidayDate = new Date(h.getDate());
            int hm = holidayDate.getMonth();
            int m = date.getMonth();
            int hd = holidayDate.getDay();
            int d = date.getDay();
            if(date.getDay()==holidayDate.getDay() && date.getMonth() == holidayDate.getMonth()){
                if(date.getYear()==holidayDate.getYear()) return true;
                else if(h.isRepeating()) return true;
            }
        }
        return false;
    }
}
