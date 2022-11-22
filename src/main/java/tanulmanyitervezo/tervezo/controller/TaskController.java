package tanulmanyitervezo.tervezo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tanulmanyitervezo.tervezo.model.Task;
import tanulmanyitervezo.tervezo.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {
    @Autowired
    TaskService service;

    @PostMapping("/add/{id}")
    public ResponseEntity<Task> add(@RequestBody Task task, @PathVariable("id") int id){
        Task newTask= service.addTask(task, id);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @GetMapping("/findallbyuser/{id}")
    public ResponseEntity<List<Task>> findAll(@PathVariable("id") int id){
        List<Task> tasks = service.findAllByUser_id(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") int id){
        Task task = service.findById(id);
        return  new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/findallbyuser/done/{id}")
    public ResponseEntity<List<Task>> findAllDone(@PathVariable("id") int id){
        List<Task> tasks = service.findAllByUser_id_Done(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/findallbyuser/undone/{id}")
    public ResponseEntity<List<Task>> findAllUndone(@PathVariable("id") int id){
        List<Task> tasks = service.findAllByUser_id_Undone(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> update(@PathVariable("id") int id, @RequestBody Task task){
        Task updatedTask = service.updateTask(task, id);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @PutMapping("/setdone/{id}")
    public ResponseEntity<Task> setDone(@PathVariable("id") int id){
        Task updatedTask = service.setDoneTask(id);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity delete(@PathVariable("id") int id){
        service.deleteTask(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}

