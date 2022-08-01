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
                "!\nЗдесь будет инфо о приюте";

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

}