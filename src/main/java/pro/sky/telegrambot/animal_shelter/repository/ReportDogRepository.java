package pro.sky.telegrambot.animal_shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.telegrambot.animal_shelter.model.ReportDogText;

public interface ReportDogRepository extends JpaRepository<ReportDogText, Long> {

    @Query(value = "SELECT * FROM report_dog_text WHERE user_id = :userId ORDER BY id DESC LIMIT 1", nativeQuery = true)
    ReportDogText endReportText(@Param("userId") long userId);


}
