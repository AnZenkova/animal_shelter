package pro.sky.telegrambot.animal_shelter.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
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

    SendMessage info(Update update);

    public SendMessage infoWorkSchedule(Update update);

    public SendMessage infoOfAddress(Update update);
}