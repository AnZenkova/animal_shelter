package pro.sky.telegrambot.animal_shelter.service.infoOfShelter;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.net.MalformedURLException;


public interface InformationOfShelterService {
    SendMessage infoWorkSchedule(Update update);

    SendMessage infoOfAddress(Update update);

    void Map() throws MalformedURLException;

    SendMessage safetyRegulations(Update update);

    SendMessage infoShelter(Update update);

    SendMessage saveUserData(Update update, String dataUser);
}
