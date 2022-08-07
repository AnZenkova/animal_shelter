package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.animal_shelter.model.UserDog;

public interface UserDogRepository extends JpaRepository<UserDog, Long> {

    UserDog findUserDogById(Long id);
}
