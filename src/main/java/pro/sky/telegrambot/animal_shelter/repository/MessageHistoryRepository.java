package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.animal_shelter.model.MessageHistory;

public interface MessageHistoryRepository extends JpaRepository<MessageHistory, Long> {

    @Query(value = "SELECT message FROM message_history ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String getEndMessage();
}
