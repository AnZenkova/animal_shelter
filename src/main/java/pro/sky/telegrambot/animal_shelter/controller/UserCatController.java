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
import pro.sky.telegrambot.animal_shelter.model.UserCat;
import pro.sky.telegrambot.animal_shelter.model.UserDog;
import pro.sky.telegrambot.animal_shelter.service.UserCat.UserCatService;

import java.util.List;

@RestController
@RequestMapping("/userCat")
public class UserCatController {

    @Autowired
    private UserCatService userCatService;


    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Поиск пользователя по id, который хочет кошку",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDog.class)
                    )
            )
    })
    @GetMapping
    public UserCat getUserCatById(@Parameter(example = "1") @RequestParam(required = false, name = "Id пользователя") Long id) {
        return userCatService.getUserCatById(id);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех пользователей, которые хотят кошку",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDog[].class)
                    )
            )
    })
    @GetMapping("/getAll")
    public List<UserCat> getAllUserDog() {
        return userCatService.getAllUsersCat();
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение данных пользователя по id, который хочет кошку",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDog.class)
                    )
            )
    })
    @PutMapping
    public UserCat editUserDog(@RequestParam(required = false, name = "Данные пользователя, который хочет кошку, для изменения") UserCat userCat) {
        return userCatService.editUserCat(userCat);
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Пользователь удален"
            )
    })
    @DeleteMapping
    public ResponseEntity deleteUserDogById(@Parameter(example = "1")@PathVariable(required = false, name = "Id пользователя") Long id) {
        userCatService.deleteUserCatById(id);
        return ResponseEntity.ok().build();
    }
}
