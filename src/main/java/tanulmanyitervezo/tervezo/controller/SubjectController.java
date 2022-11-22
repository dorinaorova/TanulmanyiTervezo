package tanulmanyitervezo.tervezo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.Homework;
import tanulmanyitervezo.tervezo.model.Period;
import tanulmanyitervezo.tervezo.model.Subject;
import tanulmanyitervezo.tervezo.model.ZH;
import tanulmanyitervezo.tervezo.services.HomeworkService;
import tanulmanyitervezo.tervezo.services.PeriodService;
import tanulmanyitervezo.tervezo.services.SubjectService;
import tanulmanyitervezo.tervezo.services.ZHService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "http://localhost:4200")
public class SubjectController {

    @Autowired
    SubjectService service;

    @Autowired
    PeriodService periodService;

    @Autowired
    ZHService zhService;

    @Autowired
    HomeworkService homeworkService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Subject>> findAll(){
        List<Subject> subjects = service.findAll();
        return  new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Optional<Subject>> findByName(@PathVariable("name") String name){
        Optional<Subject> subject = service.findByName(name);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Subject>> findById(@PathVariable("id") long id){
        Optional<Subject> subject = service.findById(id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping("/add")
    public  ResponseEntity<Subject> addSubject(@RequestBody Subject newSubject){
        Subject subject = service.addSubject(newSubject);
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteSubject(@PathVariable("id") long id){
        service.deleteSubject(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable("id") long id, @RequestBody Subject subj){
        Subject subject = service.findById(id).get();
        if(subj.getName()!=null) subject.setName(subj.getName());
        if(subj.getDescription()!=null) subject.setDescription(subj.getDescription());
        if(subj.getKredit()>0) subject.setKredit(subj.getKredit());
        Subject updateSubj = service.updateSubject(subject);
        return new ResponseEntity<>(updateSubj, HttpStatus.OK);
    }

    @PostMapping("/addperiod/{id}")
    public ResponseEntity<Period> addPeriod(@PathVariable("id") long id, @RequestBody Period period){
        Subject subject = service.findById(id).get();
        period.setSubject(subject);
        Period newPeriod = periodService.addPeriod(period);
        return new ResponseEntity<Period>(newPeriod, HttpStatus.CREATED);
    }

    @GetMapping("/findallperiods/{id}")
    public ResponseEntity<List<Period>> findAllPeriod(@PathVariable("id") long id){
        List<Period> periods = periodService.findBySubjectId(id);
        for(Period period: periods){
            period.setSubject(null);
        }
        return new ResponseEntity<>(periods, HttpStatus.OK);
    }
    @DeleteMapping("/deleteperiod/{id}")
    public  ResponseEntity deletePeriod(@PathVariable("id") int id){
        periodService.deletePeriod(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/addzh/{id}")
    public ResponseEntity<ZH> addZH(@PathVariable("id") long id, @RequestBody ZH zh){
        Subject subject = service.findById(id).get();
        zh.setSubject(subject);
        ZH newZh = zhService.addZH(zh);
        return new ResponseEntity<>(newZh, HttpStatus.CREATED);
    }
    @DeleteMapping("/deletezh/{id}")
    public ResponseEntity deleteZh(@PathVariable("id") int id){
        zhService.deleteZH(id);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/findallzhforsubject/{id}")
    public ResponseEntity<List<ZH>> findAllZH_Subject(@PathVariable("id") long id){
        List<ZH> zhs = zhService.findBySubjectId(id);
        for(ZH zh: zhs){
            zh.setSubject(null);
        }
        Collections.sort(zhs);
        return new ResponseEntity<>(zhs, HttpStatus.OK);
    }

    @GetMapping("/findallzhforsemester/{semesterId}/{userId}")
    public ResponseEntity<List<ZH>> findAllZH_User(@PathVariable("userId") int userId, @PathVariable("semesterId") int semesterId){
        List<ZH> zhs = zhService.findByUserId(userId, semesterId);
        for(ZH zh: zhs){
            zh.setSubject(null);
        }
        Collections.sort(zhs);
        return new ResponseEntity<>(zhs, HttpStatus.OK);
    }

    @PostMapping("/addhomework/{id}")
    public ResponseEntity<Homework> addHomework(@PathVariable("id") long id, @RequestBody Homework homework){
        Subject subject = service.findById(id).get();
        homework.setSubject(subject);
        Homework newHomework = homeworkService.addHomework(homework);
        return new ResponseEntity<>(newHomework, HttpStatus.CREATED);
    }

    @GetMapping("/findallhomework/{id}")
    public ResponseEntity<List<Homework>> findAllHomework(@PathVariable("id") long id){
        List<Homework> homeworks = homeworkService.findBySubjectId(id);
        for(Homework homework: homeworks){
            homework.setSubject(null);
        }
        Collections.sort(homeworks);
        return new ResponseEntity<>(homeworks, HttpStatus.OK);
    }

    @DeleteMapping("/deleteHomework/{id}")
    public ResponseEntity deleteHomework(@PathVariable("id") int id){
        homeworkService.deleteHomework(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}