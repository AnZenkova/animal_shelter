package pro.sky.telegrambot.animal_shelter.service.messageHandler;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Сервис обработки сообщений, отправленных пользователем
 */
public interface MessageHandlerService {

    SendMessage choiceOfShelter(Update update);

    /**
     * Обработка сообщения пользователя
     *
     * @param update Объект сообщения чата
     */
    SendMessage handle(Update update);

}
