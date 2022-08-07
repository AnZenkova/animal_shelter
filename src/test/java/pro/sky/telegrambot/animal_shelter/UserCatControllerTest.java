package pro.sky.telegrambot.animal_shelter;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telegrambot.animal_shelter.controller.UserCatController;
import pro.sky.telegrambot.animal_shelter.model.UserCat;
import pro.sky.telegrambot.animal_shelter.repository.UserCatRepository;
import pro.sky.telegrambot.animal_shelter.service.UserCat.UserCatServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserCatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserCatRepository userCatRepository;

    @SpyBean
    UserCatServiceImpl userCatService;

    @InjectMocks
    private UserCatController userCatController;

    @Test
    public void getAllUserTest() throws Exception{

        Long id = 1L;
        String userName = "khvbka";
        String firstName = "hhcdh";
        String lastName = "dbhjs";

        UserCat user = new UserCat();
        user.setId(id);
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        List<UserCat> users = new ArrayList<>();
        users.add(user);

        when(userCatRepository.save(any(UserCat.class))).thenReturn(user);
        when(userCatRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/userCat/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.username").value(userName))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName));
    }

    @Test
    public void editUserByIdTest() throws Exception{

        Long id = 1L;
        String userName = "khvbka";
        String firstName = "hhcdh";
        String lastName = "dbhjs";

        JSONObject userDogObject = new JSONObject();
        userDogObject.put("username", userName);
        userDogObject.put("firstName", firstName);
        userDogObject.put("lastName", lastName);

        UserCat editUserCat = new UserCat();
        editUserCat.setId(id);
        editUserCat.setUsername(userName);
        editUserCat.setFirstName(firstName);
        editUserCat.setLastName(lastName);


        when(userCatRepository.save(any(UserCat.class))).thenReturn(editUserCat);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/userCat")
                        .content(userDogObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.username").value(userName))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName));
    }

    @Test
    public void deleteUserCatTest() throws Exception {

        Long id = 1L;
        String userName = "khvbka";
        String firstName = "hhcdh";
        String lastName = "dbhjs";

        UserCat user = new UserCat();
        user.setId(id);
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userCatRepository.save(user);
        doNothing().when(userCatRepository).deleteById(id);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/userCat/" + id))
                .andExpect(status().isOk());
    }
}
