package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import pro.sky.telegrambot.animal_shelter.model.User;

import java.util.Optional;

/**
 * Репозиторий для работы с моделью(моделями) пользователя в БД
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Получение пользователя по Id чата из БД
     *
     * @param chatId Идентификатор чата
     *
     * @return User
     */
    User findByChatIdEquals(@NonNull long chatId);

}
