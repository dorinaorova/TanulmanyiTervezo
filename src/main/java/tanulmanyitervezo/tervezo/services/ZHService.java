package tanulmanyitervezo.tervezo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tanulmanyitervezo.tervezo.Models.ZH;
import tanulmanyitervezo.tervezo.Repository.ZHRepository;

import java.util.List;

@Service
public class ZHService  {

    @Autowired
    private ZHRepository repository;

    public ZH addZH(ZH zh){
        return repository.save(zh);
    }

    public List<ZH> findBySubjectId(long id){
        return repository.findBySubject_Id(id);
    }

    public void deleteZH(int id){
        repository.deleteById(id);
    }

    public void deleteAllBySubject_id(long id){
        repository.deleteBySubject_Id(id);
    }
}
