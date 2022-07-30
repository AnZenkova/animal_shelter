package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import pro.sky.telegrambot.animal_shelter.model.User;

/**
 * Репозиторий для работы с пользователем в БД
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Получение чата по Id из БД
     * @param chatId
     * @return User
     */
    User findByChatIdEquals(@NonNull long chatId);

}
