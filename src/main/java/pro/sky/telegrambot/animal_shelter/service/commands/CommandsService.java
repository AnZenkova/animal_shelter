package pro.sky.telegrambot.animal_shelter.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface CommandsService {

    SendMessage start(Update update);
}