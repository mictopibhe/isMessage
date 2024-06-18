package pl.davidduke.ismessage.repository;

import org.springframework.data.repository.CrudRepository;
import pl.davidduke.ismessage.entity.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);

}
