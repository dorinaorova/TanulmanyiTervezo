package tanulmanyitervezo.tervezo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.Holiday;
import tanulmanyitervezo.tervezo.services.HolidayService;

import java.util.List;

@RestController
@RequestMapping("/holiday")
@CrossOrigin(origins = "http://localhost:4200")
public class HolidayController {

    @Autowired
    HolidayService service;

    @GetMapping("/findall")
    public ResponseEntity<List<Holiday>> findAll(){
        List<Holiday> holidays = service.findallHoliday();
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @GetMapping("/findbydate/{id}")
    public ResponseEntity<List<Holiday>> findByDate(@PathVariable("id") int id){
        List<Holiday> holidays = service.findByDate(id);
        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Holiday> add(@RequestBody Holiday holiday){
        Holiday newHoliday = service.addHoliday(holiday);
        return new ResponseEntity<>(newHoliday, HttpStatus.CREATED);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id){
        service.deleteHoliday(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/isholiday/{date}")
    public ResponseEntity<Boolean> isHoliday(@PathVariable("date") long date){
        Boolean result = service.isHoliday(date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
