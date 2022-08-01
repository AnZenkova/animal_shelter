package pro.sky.telegrambot.animal_shelter.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.animal_shelter.service.messageHandler.MessageHandlerService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Основной сервис в котором происходит распределение, по работе с чатом, на другие сервисы
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;
    private final MessageHandlerService messageHandlerService;

    public TelegramBotUpdatesListener(
            TelegramBot telegramBot,
            MessageHandlerService messageHandlerService
    ) {
        this.telegramBot = telegramBot;
        this.messageHandlerService = messageHandlerService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            telegramBot.execute(messageHandlerService.handle(update));
        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}