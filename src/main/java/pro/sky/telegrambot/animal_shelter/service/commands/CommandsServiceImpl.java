package pro.sky.telegrambot.animal_shelter.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandsServiceImpl implements CommandsService {

    public SendMessage start(Update update) {
        String message = "Привет, " + update.message().from().username() + " !\n" +
                "Я пока мало чем могу помочь, но я учусь!";

        //Создание клавиатуры
        Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new String[]{"О приюте", "Как взять собаку из приюта"},
                new String[]{"Прислать отчет о питомце", "Позвать волонтёра"})
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);

        return new SendMessage(update.message().chat().id(), message).replyMarkup(replyKeyboardMarkup);
    }

}