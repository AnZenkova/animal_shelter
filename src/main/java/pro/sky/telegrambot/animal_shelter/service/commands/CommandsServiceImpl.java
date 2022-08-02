package pro.sky.telegrambot.animal_shelter.service.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.constant.Keyboards;

@Service
public class CommandsServiceImpl implements CommandsService {

    @Override
    public SendMessage start(Update update) {
        String message = "Привет, " + update.message().from().firstName() +
                "!\nВыбери, пожалуйста, один из пунктов!";

        return new SendMessage(update.message().chat().id(), message)
                .replyMarkup(Keyboards.SHELTER_KEYBOARD);
    }

    @Override
    public SendMessage aboutShelter(Update update) {
        String message = "Привет, " + update.message().from().firstName() +
                "!\nЯ могу кое-что рассказать тебе о нашем приюте! \nВыбери что бы ты хотел узнать";

        return new SendMessage(update.message().chat().id(), message).replyMarkup(Keyboards.ABOUT_SHELTER_KEYBOARD);
    }

    @Override
    public SendMessage howGetDogFromShelter(Update update) {
        String message = "Привет, " + update.message().from().firstName() +
                "!\nЗдесь будет инфо о том как взять собаку из приюта";

        return new SendMessage(update.message().chat().id(), message);
    }

    @Override
    public SendMessage petReport(Update update) {
        String message = "Привет, " + update.message().from().firstName() +
                "!\nЗдесь можно будет прислать отчет о питомце";

        return new SendMessage(update.message().chat().id(), message);
    }

    @Override
    public SendMessage volunteerCall(Update update) {
        String message = "Привет, " + update.message().from().username() +
                "!\nЗдесь можно будет позвать волонтера";

        return new SendMessage(update.message().chat().id(), message);
    }

//    public SendMessage info(Update update) {
//        if (update.message().text().equals("О приюте")) {
//            Keyboard replyKeyboardMarkup = new ReplyKeyboardMarkup(
//                    new String[]{"Расписание работы", "Адрес приюта", "Как добраться"},
//                    new String[]{"Правила безопасности", "Информация о приюте", "Назад"})
//                    .oneTimeKeyboard(true)
//                    .resizeKeyboard(true)
//                    .selective(true);
//            return new SendMessage(update.message().chat().id(), "Информация").replyMarkup(replyKeyboardMarkup);
//        } else if (update.message().text().equals("Как взять собаку из приюта")) {
//
//        }
//        return new SendMessage(update.message().chat().id(), "3213");
//    }

//    public SendMessage infoWorkSchedule(Update update) {
//        return new SendMessage(update.message().chat().id(),
//                "•Понедельник с 9:00 до 18:00\n" +
//                        "•Вторник с 9:00 до 18:00\n" +
//                        "•Среда с 9:00 до 18:00\n" +
//                        "•Четверг с 9:00 до 18:00\n" +
//                        "•Пятница с 9:00 до 18:00\n" +
//                        "•Суббота с 10:00 до 17:00\n" +
//                        "•Воскресенье - выходной");
//    }
//
//    public SendMessage infoOfAddress(Update update) {
//        return new SendMessage(update.message().chat().id(), "г. Москва, ул. Родниковая, вл26");
//    }
//
//    public void Map() throws MalformedURLException {
//        URL url = new URL("https://yandex.ru/maps/?ll=86.307640%2C51.697250&mode=routes&rtext=43.096069%2C133.120554~55.624728%2C37.376078&rtt=auto&ruri=~ymapsbm1%3A%2F%2Forg%3Foid%3D1476817960&z=2");
//
//    }

}