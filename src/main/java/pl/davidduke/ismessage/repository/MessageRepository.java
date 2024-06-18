package pl.davidduke.ismessage.repository;

import org.springframework.data.repository.CrudRepository;
import pl.davidduke.ismessage.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
