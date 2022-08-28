package pro.sky.telegrambot.animal_shelter.controller;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.animal_shelter.model.UserDog;
import pro.sky.telegrambot.animal_shelter.service.userDog.UserDogService;

import java.util.List;

@RestController
@RequestMapping("/userDog")
public class UserDogController {

    @Autowired
    private UserDogService userDogService;

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Поиск пользователя по id, который хочет собаку",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDog.class)
                    )
            )
    })

    @GetMapping
    public UserDog getUserDogById(@Parameter(example = "1") @RequestParam(required = false, name = "Id пользователя") Long id) {
        return userDogService.getUserDogById(id);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех пользователей, которые хотят собаку",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDog[].class)
                    )
            )
    })
    @GetMapping("/getAll")
    public List<UserDog> getAllUserDog() {
        return userDogService.getAllUsersDog();
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение данных пользователя по id, который хочет собаку",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDog.class)
                    )
            )
    })
    @PutMapping
    public UserDog editUserDog(@RequestParam(required = false, name = "Данные пользователя, который хочет сабаку, для изменения") UserDog userDog) {
        return userDogService.editUserDog(userDog);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь удален"
            )
    })
    @DeleteMapping
    public ResponseEntity deleteUserDogById(@Parameter(example = "1")@PathVariable(required = false, name = "Id пользователя") Long id) {
        userDogService.deleteUserDogById(id);
        return ResponseEntity.ok().build();
    }
}
