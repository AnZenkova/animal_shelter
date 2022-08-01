package pro.sky.telegrambot.animal_shelter.constant;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public final class Keyboards {

    private Keyboards() {
    }

    //Создание таблицы основного меню
    public static final Keyboard SHELTER_KEYBOARD = new ReplyKeyboardMarkup(
            new String[]{"О приюте", "Как взять собаку из приюта"},
            new String[]{"Прислать отчет о питомце", "Позвать волонтёра"})
            .oneTimeKeyboard(true)
            .resizeKeyboard(true)
            .selective(true);

    public static final Keyboard ABOUT_SHELTER_KEYBOARD = new ReplyKeyboardMarkup(
            new String[]{"1", "2", "3"},
            new String[]{"4", "5"})
            .oneTimeKeyboard(true)
            .resizeKeyboard(true)
            .selective(true);

}
