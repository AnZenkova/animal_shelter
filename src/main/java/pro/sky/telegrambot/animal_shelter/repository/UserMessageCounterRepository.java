package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import pro.sky.telegrambot.animal_shelter.model.UserMessageCounter;

/**
 * Репозиторий для работы со счетчиком в БД
 */
public interface UserMessageCounterRepository extends JpaRepository<UserMessageCounter, Long> {

    /**
     * Получение количества сообщений в чате
     * @param userId
     * @return UserMessageCounter
     */
    UserMessageCounter findByUserIdEquals(@NonNull long userId);

}
