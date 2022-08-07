package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.animal_shelter.model.UserCat;

public interface UserCatRepository extends JpaRepository<UserCat, Long> {

    UserCat findUserCatById(Long id);
}
