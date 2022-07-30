package pro.sky.telegrambot.animal_shelter.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class CommandsServiceImpl implements CommandsService {

    public SendMessage start(Update update) {
        return new SendMessage(
                update.message().chat().id(),
                getTextMessage(update.message().from().username())
        );
    }

    private String getTextMessage(String username) {
        return "Привет, " + username + " !\n" +
                "Я пока мало чем могу помочь, но я учусь!";
    }

}