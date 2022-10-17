package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.Holiday;
import tanulmanyitervezo.tervezo.Repository.HolidayRepository;

import java.util.Date;
import java.util.List;

@Service
public class HolidayService {
    @Autowired
    HolidayRepository repository;

    public Holiday addHoliday(Holiday holiday){
        return repository.save(holiday);
    }

    public List<Holiday> findallHoliday(){
        return repository.findAll();
    }

    public List<Holiday> findByDate(Date date){
        return repository.findAllByDate(date);
    }
}
