package de.telran.onlineshop.integration;

import de.telran.onlineshop.service.CategoriesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest  // запускаем контейнер Спринг
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@ActiveProfiles(profiles = { "dev"})
public class CategoriesIntegrationTest {

    @Autowired
    private MockMvc mockMvc; // для имитации запросов пользователей


    @Test
    void getAllCategories() throws Exception {
        this.mockMvc.perform(get("/categories"))
                .andDo(print()) //печать лога вызова
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..categoryID").exists())
                .andExpect(jsonPath("$..name").exists())
        ;
    }

}
