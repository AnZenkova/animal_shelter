package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import pro.sky.telegrambot.animal_shelter.model.UserMessageCounter;

public interface UserMessageCounterRepository extends JpaRepository<UserMessageCounter, Long> {

    UserMessageCounter findByUserIdEquals(@NonNull long userId);

}
