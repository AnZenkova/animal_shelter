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
            new String[]{"Информация о приюте", "Адрес приюта", "Как добраться"},
            new String[]{"Расписание работы", "Правила безопасности", "Оставить данные для связи"})
            .oneTimeKeyboard(true)
            .resizeKeyboard(true)
            .selective(true);

    public static final Keyboard ABOUT_HOW_TO_TAKE_ANIMAL_FROM_SHELTER = new ReplyKeyboardMarkup(
            new String[]{"Правила знакомства с собакой", "Список документов", "О перевозке"},
            new String[]{"Обустройство дома для щенка"})
            .oneTimeKeyboard(true)
            .resizeKeyboard(true)
            .selective(true);

}
