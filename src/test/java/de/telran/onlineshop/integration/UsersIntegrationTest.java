package de.telran.onlineshop.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.onlineshop.dto.UserDto;
import de.telran.onlineshop.entity.*;
import de.telran.onlineshop.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest  // запускаем контейнер Спринг
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles(profiles = {"dev"})
public class UsersIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersRepository usersRepositoryMock;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getPartSystemAllUsersTest() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
        ;
    }

    @Test
    void getIntegrationAllUsersTest() throws Exception { // имитрируем запрос в БД
        when(usersRepositoryMock.findAll()).thenReturn(List.of(
                new UsersEntity(
                        1L,
                        "Вася Пупкин",
                        "vasya@i.com",
                        "+491601234567",
                        "Password1",
                        Role.CLIENT,
                        new CartEntity(),
                        new HashSet<FavoritesEntity>(),
                        new HashSet<OrdersEntity>(),
                        new HashSet<AddressEntity>()
                ))
        );

        this.mockMvc.perform(get("/users"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..userID").exists())
                .andExpect(jsonPath("$..name").exists());
    }

    @Test
    void getIntegrationUserByIDTest() throws Exception {
        Long testId = 1L;
        when(usersRepositoryMock.findById(testId)).thenReturn(Optional.of(new UsersEntity(
                1L,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1",
                Role.CLIENT,
                new CartEntity(),
                new HashSet<FavoritesEntity>(),
                new HashSet<OrdersEntity>(),
                new HashSet<AddressEntity>()
        )));

        this.mockMvc.perform(get("/users/find/1"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userID").exists())
                .andExpect(jsonPath("$.name").exists());
        ;

    }

    @Test
    void getIntegrationUserByNameTest() throws Exception {
        String testName = "Vasia";
        when(usersRepositoryMock.findByName(testName)).thenReturn(new UsersEntity(
                1L,
                "Vasia",
                "Vasia@i.com",
                "+491601234567",
                "Password1",
                Role.CLIENT,
                new CartEntity(),
                new HashSet<FavoritesEntity>(),
                new HashSet<OrdersEntity>(),
                new HashSet<AddressEntity>()
        ));

        this.mockMvc.perform(get("/users/get?name=Vasia"))
                .andDo(print()) //печать лога вызова
                .andExpect(jsonPath("$.userID").exists())
                .andExpect(jsonPath("$.name").exists());
        ;

    }

    @Test
    void createIntegrationUsersTest() throws Exception {

        UserDto usersDtoInput = new UserDto(
                null,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1"
        );

        when(usersRepositoryMock.save(any(UsersEntity.class))).thenReturn(
                new UsersEntity(
                        1L,
                        "Вася Пупкин",
                        "vasya@i.com",
                        "+491601234567",
                        "Password1",
                        Role.CLIENT,
                        new CartEntity(),
                        new HashSet<FavoritesEntity>(),
                        new HashSet<OrdersEntity>(),
                        new HashSet<AddressEntity>()
                ));

        this.mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDtoInput))) // jackson: object -> json
                .andDo(print())
                .andExpect(status().isCreated())
        ;
    }

    @Test
    void updateIntegrationUserTest() throws Exception {
        UserDto usersDtoUpdate = new UserDto(
                3L,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1"
        );

        when(usersRepositoryMock.save(any(UsersEntity.class))).thenReturn(
                new UsersEntity(
                        3L,
                        "Вася Пупкин",
                        "vasya@i.com",
                        "+491601234567",
                        "Password1",
                        Role.CLIENT,
                        new CartEntity(),
                        new HashSet<FavoritesEntity>(),
                        new HashSet<OrdersEntity>(),
                        new HashSet<AddressEntity>()
                ));

        this.mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usersDtoUpdate))) // jackson: object -> json
                .andDo(print())
                .andExpect(jsonPath("$.userID").exists())
                .andExpect(jsonPath("$.name").exists())
        ;
    }

    @Test
    void deleteIntegrationUserTest() throws Exception {
        when(usersRepositoryMock.findById(3L)).thenReturn(Optional.of(new UsersEntity(
                3L,
                "Вася Пупкин",
                "vasya@i.com",
                "+491601234567",
                "Password1",
                Role.CLIENT,
                new CartEntity(),
                new HashSet<FavoritesEntity>(),
                new HashSet<OrdersEntity>(),
                new HashSet<AddressEntity>()
        )));

        this.mockMvc.perform(delete("/users/3"))
                .andDo(print());//печать лога вызова
        verify(usersRepositoryMock).delete(any(UsersEntity.class));
    }
}
