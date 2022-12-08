package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Holiday;
import tanulmanyitervezo.tervezo.model.Semester;
import tanulmanyitervezo.tervezo.repository.HolidayRepository;
import tanulmanyitervezo.tervezo.repository.SemesterRepository;

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

    public List<Holiday> findBySemester(int id) throws Exception {
        Optional<Semester> semester = semesterRepository.findById(id);
        if(semester.isPresent()) {
            Date start = new Date(semester.get().getStart());
            Date end = new Date(semester.get().getEnd());
            List<Holiday> all = repository.findAll();
            ArrayList<Holiday> holidays = new ArrayList<>();

            for (Holiday h : all) {
                Date date = new Date(h.getDate());
                if (betweenDates(date, start, end)) holidays.add(h);
                else if (h.isRepeating()) {
                    Calendar cal = Calendar.getInstance();
                    cal.set(start.getYear(), date.getMonth(), date.getDay());
                    Date holidayDate = cal.getTime();
                    if (betweenDates(holidayDate, start, end)) holidays.add(h);
                    else {
                        cal.set(end.getYear(), date.getMonth(), date.getDay());
                        holidayDate = cal.getTime();
                        if (betweenDates(holidayDate, start, end)) holidays.add(h);
                    }
                }
            }
            Collections.sort(holidays);
            return holidays;
        }
        else throw new Exception("NOT FOUND");
    }

    public void deleteHoliday(int id) throws Exception {
        try {
            repository.deleteById(id);
        }catch (Exception e){
            throw new Exception("NOT FOUND");
        }
    }

    private boolean betweenDates(Date holiday, Date start, Date end){
        if(holiday.after(start) && holiday.before(end)) return true;
        else return false;
    }

    public boolean isHoliday(long dateTs){
        List<Holiday> holidays = findallHoliday();
        Date date = new Date(dateTs);
        for(Holiday h: holidays){
            Date holidayDate = new Date(h.getDate());
            if(date.getDate()==holidayDate.getDate() && date.getMonth() == holidayDate.getMonth()){
                if(date.getYear()==holidayDate.getYear()) return true;
                else if(h.isRepeating()) return true;
            }
        }
        return false;
    }
}
