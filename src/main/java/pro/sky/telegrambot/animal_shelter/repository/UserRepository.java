package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import pro.sky.telegrambot.animal_shelter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByChatIdEquals(@NonNull long chatId);

}
