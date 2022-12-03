package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.model.Task;
import tanulmanyitervezo.tervezo.model.User;
import tanulmanyitervezo.tervezo.repository.TaskRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository repository;

    @Autowired
    UserService userService;

    public Task addTask(Task task, int id) throws Exception {
        Optional<User> user = userService.findById(id);
        if(user.isPresent()) {
            task.setDone(false);
            task.setUser(user.get());
            return repository.save(task);
        }
        else throw new Exception("NOT FOUND");
    }

    public Optional<Task> findById(int id, int user_id) throws Exception{
        Optional<Task> task = repository.findById(id);
        if(task.isPresent()){
            if(task.get().getUser().getId()==user_id) return task;
            else throw new Exception("WRONG USER ID");
        }
        else throw new Exception("NOT FOUND");
    }

    public List<Task> findAllByUser_id(int id){
        List<Task> tasks =  repository.findByUser_Id(id);
        Collections.sort(tasks);
        return tasks;
    }

    public List<Task> findAllByUser_id_Done(int id){
        List<Task> tasks = findAllByUser_id(id);
        ArrayList<Task> done = new ArrayList<>();
        for (Task t: tasks){
            if(t.isDone()) done.add(t);
        }
        Collections.sort(done);
        return done;
    }
    public List<Task> findAllByUser_id_Undone(int id){
        List<Task> tasks = findAllByUser_id(id);
        ArrayList<Task> undone = new ArrayList<>();
        for (Task t: tasks){
            if(!t.isDone()) undone.add(t);
        }
        Collections.sort(undone);
        return undone;
    }

    public Task updateTask(Task task, int id) throws Exception {
        Optional<Task> t= repository.findById(id);
        if(t.isPresent()){
            if(task.getDate()!=0) t.get().setDate(task.getDate());
            if(task.getDescription()!=null && !task.getDescription().equals("") ) t.get().setDescription(task.getDescription());
            if(task.getName()!=null && !task.getName().equals("")) t.get().setName(task.getName());
            return repository.save(t.get());
        }
        else throw new Exception("NOT FOUND");
    }

    public Task setDoneTask(int id) throws Exception {
        Optional<Task> task = repository.findById(id);
        if(task.isPresent()){
            task.get().setDone(true);
            return repository.save(task.get());
        }
        else throw new Exception("NOT FOUND");
    }

    public void deleteTask(int id) throws Exception {
        try {
            repository.deleteById(id);
        }catch (Exception e){
            throw new Exception("NOT FOUND");
        }
    }

}
