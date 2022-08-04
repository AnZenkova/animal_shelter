package pro.sky.telegrambot.animal_shelter.service.infoOfShelter;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;


public interface InformationOfShelterService {

    /**
     * Обработка команд внутри команды `О приюте`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage distribution(Update update);

    /**
     * Обработка команды `Расписание`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage infoWorkSchedule(Update update);

    /**
     * Обработка команды `Адрес приюта`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage infoOfAddress(Update update);

    SendMessage map(Update update);

    /**
     * Обработка команды `Правила безопасности в приюте`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage safetyRegulations(Update update);

    /**
     * Обработка команды `Информация о приюте`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage infoShelter(Update update);

    /**
     * Обработка команды `Сохранить данные пользователя`
     *
     * @param update Объект сообщения чата
     * @param dataUser Контактные данные пользователя
     *
     * @return SendMessage
     */
    SendMessage saveUserData(Update update, String dataUser);
}
