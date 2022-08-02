package pro.sky.telegrambot.animal_shelter.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

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

    public SendMessage info(Update update) {
        if (update.message().text().equals("О приюте")) {
            Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
                    new String[]{"Расписание работы", "Адрес приюта", "Как добраться"},
                    new String[]{"Правила безопастности", "Информация о приюте", "Назад"})
                    .oneTimeKeyboard(true)
                    .resizeKeyboard(true)
                    .selective(true);
            return new SendMessage(update.message().chat().id(), "Информация").replyMarkup(replyKeyboardMarkup);
        } else if (update.message().text().equals("Как взять собаку из приюта")) {

        }
        return new SendMessage(update.message().chat().id(), "3213");
    }

    public SendMessage infoWorkSchedule(Update update) {
        return new SendMessage(update.message().chat().id(), "Понедельник с 9:00 до 18:00; " +
                "Вторник с 9:00 до 18:00; " +
                "Среда с 9:00 до 18:00; " +
                "Четверг с 9:00 до 18:00; " +
                "Пятница с 9:00 до 18:00; " +
                "Суббота с 10:00 до 17:00; " +
                "Воскресенье выходной");
    }

    public SendMessage infoOfAddress(Update update) {
        return new SendMessage(update.message().chat().id(), "г. Москва, ул. Родниковая, вл26");
    }

    public void Map() throws MalformedURLException {
        URL url = new URL("https://yandex.ru/maps/?ll=86.307640%2C51.697250&mode=routes&rtext=43.096069%2C133.120554~55.624728%2C37.376078&rtt=auto&ruri=~ymapsbm1%3A%2F%2Forg%3Foid%3D1476817960&z=2");

    }

}