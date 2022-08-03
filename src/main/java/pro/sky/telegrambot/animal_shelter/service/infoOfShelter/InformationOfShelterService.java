package pro.sky.telegrambot.animal_shelter.service.infoOfShelter;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;


public interface InformationOfShelterService {
    SendMessage infoWorkSchedule(Update update);

    SendMessage infoOfAddress(Update update);

    SendMessage map(Update update);

    SendMessage safetyRegulations(Update update);

    SendMessage infoShelter(Update update);

    SendMessage saveUserData(Update update, String dataUser);
}
