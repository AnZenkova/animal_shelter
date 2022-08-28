package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.animal_shelter.model.UserCatCommunication;

public interface UserCatCommunicationRepository extends JpaRepository<UserCatCommunication, Long> {
}
