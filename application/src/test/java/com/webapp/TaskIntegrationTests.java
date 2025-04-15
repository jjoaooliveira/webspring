package com.webapp;

import com.webapp.repository.TaskRepository;
import com.webapp.repository.entity.TaskEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TaskIntegrationTests {

    @Autowired
    WebApplicationContext context;

    @Autowired
    TaskRepository taskRepository;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        taskRepository.save(new TaskEntity(
                UUID.fromString("92907c06-f2eb-49b9-9da4-069bed23b3eb"),
                "Java",
                "Create Controller Tests",
                OffsetDateTime.parse("2025-01-01T00:00:00-02:00"),
                OffsetDateTime.parse("2025-01-01T23:59:59-02:00"),
                true)
        );

        taskRepository.save(new TaskEntity(
                UUID.fromString("c44a929f-d124-4884-8d1c-ff151f9723df"),
                "Docker",
                "Create Docker Compose File",
                OffsetDateTime.parse("2025-01-01T00:00:00-01:00"),
                OffsetDateTime.parse("2025-01-01T23:59:59-01:00"),
                false)
        );
    }

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    @DisplayName("Test System Layers Integration")
    void givenHttpGetRequest_whenGetAllTask_thenReturnOkStatus() throws Exception {
        //arrange
        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/task"));

        //assert
        actual.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test System Get Task Response Type")
    void givenHttpGetRequest_whenGetAllTask_thenReturnJsonResponseType() throws Exception {
        //arrange
        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/task"));

        //assert
        actual.andExpect(content().contentTypeCompatibleWith(MediaType.valueOf("application/hal+json")));
    }

    @Test
    @DisplayName("Test System Get Task Response")
    void givenHttpGetRequest_whenGetAllTask_thenReturnCorrectJsonResponse() throws Exception {
        //arrange
        String expectedId1 = "92907c06-f2eb-49b9-9da4-069bed23b3eb";
        String expectedId2 = "c44a929f-d124-4884-8d1c-ff151f9723df";
        String expectedTitle1 = "Java";
        String expectedTitle2 = "Docker";
        String expectedContent1 = "Create Controller Tests";
        String expectedContent2 = "Create Docker Compose File";
        String expectedCreation1 = "2024-12-31T23:00:00-03:00";
        String expectedExpiration1 = "2025-01-01T22:59:59-03:00";
        String expectedCreation2 = "2024-12-31T22:00:00-03:00";
        String expectedExpiration2 = "2025-01-01T21:59:59-03:00";

        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/task"))
                .andDo(print());

        //assert
        actual.andExpect(jsonPath("$.*.*.[*].id",
                containsInAnyOrder(expectedId1, expectedId2)))
            .andExpect(jsonPath("$.*.*.[*].title",
                containsInAnyOrder(expectedTitle1, expectedTitle2)))
            .andExpect(jsonPath("$.*.*.[*].content",
                containsInAnyOrder(expectedContent1, expectedContent2)))
            .andExpect(jsonPath("$.*.*.[*].creation",
                containsInAnyOrder(expectedCreation1, expectedCreation2)))
            .andExpect(jsonPath("$.*.*.[*].expiration",
                containsInAnyOrder(expectedExpiration1, expectedExpiration2)));
    }

    @Test
    @DisplayName("Test System Get Task By Title Response")
    void givenHttpGetRequest_whenGetTaskByTitle_thenReturnCorrectJsonResponse() throws Exception {
        //arrange
        String expectedId = "92907c06-f2eb-49b9-9da4-069bed23b3eb";
        String expectedTitle = "Java";
        String expectedContent = "Create Controller Tests";
        String expectedCreation = "2024-12-31T23:00:00-03:00";

        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/task/title?title=Java"))
                .andDo(print()); //TODO remover

        //assert
        actual.andExpect(jsonPath("$.*.*.[*].id",
                    containsInAnyOrder(expectedId)))
            .andExpect(jsonPath("$.*.*.[*].title",
                    containsInAnyOrder(expectedTitle)))
            .andExpect(jsonPath("$.*.*.[*].content",
                    containsInAnyOrder(expectedContent)))
            .andExpect(jsonPath("$.*.*.[*].creation",
                    containsInAnyOrder(expectedCreation)));
    }

    @Test
    @DisplayName("Test System Post Task Response")
    void givenHttpGetRequest_whenPostTask_thenReturnCorrectJsonResponse() throws Exception {
        //arrange
        String expectedTitle = "Spring";
        String expectedContent = "Add spring eureka dependency";
        String expectedExpiration = "2025-01-01T01:00:00-03:00";

        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.post("/task")
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": \"\", \"title\": \"Spring\", \"content\": \"Add spring eureka dependency\", \"creationDate\": \"\", \"expirationDate\": \"2025-01-01T00:00:00-04:00\", \"completed\": \"false\"}"))
                .andDo(print());

        //assert
        actual.andExpect(jsonPath("$.title", is(expectedTitle)))
                .andExpect(jsonPath("$.content", is(expectedContent)))
                .andExpect(jsonPath("$.expiration", is(expectedExpiration)));
    }
}
