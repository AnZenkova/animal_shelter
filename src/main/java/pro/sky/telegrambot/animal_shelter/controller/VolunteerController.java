package pro.sky.telegrambot.animal_shelter.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;
import pro.sky.telegrambot.animal_shelter.model.User;
import pro.sky.telegrambot.animal_shelter.repository.UserRepository;

@Tag(name = "VolunteerController", description = "Контроллер для работы волонтёра с пользователями")
@RestController
@RequestMapping("/volunteerController")
public class VolunteerController {
    private final UserRepository userRepository;
    private final TelegramBot telegramBot;

    public VolunteerController(UserRepository userRepository, TelegramBot telegramBot) {
        this.userRepository = userRepository;
        this.telegramBot = telegramBot;
    }


    @Operation(summary = "Отправление предупреждения усыновителю", description = "Отправление предупреждения усыновителю")
    @GetMapping("/badReportMessage")
    public ResponseEntity BadReportMessage(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User с id " + userId + "не найден"));

        telegramBot.execute(new SendMessage(user.getChatId(), "Дорогой усыновитель, мы заметили, " +
                "что ты заполняешь отчет не так подробно, как необходимо. " +
                "Пожалуйста, подойди ответственнее к этому занятию. " +
                "В противном случае волонтеры приюта будут обязаны самолично проверять условия содержания животного"));
        return ResponseEntity.ok().build();

    }

    @Operation(summary = "Отправление поздравления усыновителю", description = "Отправление поздравления усыновителю")
    @GetMapping("/congratulation")
    public ResponseEntity Congratulation(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User с id " + userId + "не найден"));

        telegramBot.execute(new SendMessage(user.getChatId(), "Наш приют поздравляет тебя с успешным прохождением испытательного срока! " +
                "Теперь мы знаем, что питомец в надежных руках!"));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Сообщение усыновителю о продлении испытательного срока", description = "Сообщение усыновителю о продлении испытательного срока")
    @GetMapping("/probationPeriodExtension")
    public ResponseEntity ProbationPeriodExtension(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User с id " + userId + "не найден"));

        telegramBot.execute(new SendMessage(user.getChatId(), "Извините но наш приют не до конца убедился в том что наш питомец в надежных руках! " +
                "Мы продлеваем вам испытательный срок ещё на 15 дней!"));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Сообщение усыновителю об отказе в усыновлении", description = "Сообщение усыновителю об отказе в усыновлении")
    @GetMapping("/refusalToAdopt")
    public ResponseEntity RefusalToAdopt(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User с id " + userId + "не найден"));

        telegramBot.execute(new SendMessage(user.getChatId(), "Извините но наш приют принял решение отказать вам в усыновлении питомца. " +
                "Вам необходимо вернуть его в приют!"));
        return ResponseEntity.ok().build();
    }
}
