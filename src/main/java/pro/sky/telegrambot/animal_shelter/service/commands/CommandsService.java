package pro.sky.telegrambot.animal_shelter.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface CommandsService {

    /**
     * Обработка команды `start`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage start(Update update);

    /**
     * Обработка команды `О приюте`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage aboutShelter(Update update);

    /**
     * Обработка команды `Как взять собаку из приюта`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage howGetDogFromShelter(Update update);

    /**
     * Обработка команды `Прислать отчет о питомце`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage petReport(Update update);

    /**
     * Обработка команды `Позвать волонтёра`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    SendMessage volunteerCall(Update update);

    /**
     * Обработка команды `Назад`
     *
     * @param update Объект сообщения чата
     *
     * @return SendMessage
     */
    public SendMessage back(Update update);
}