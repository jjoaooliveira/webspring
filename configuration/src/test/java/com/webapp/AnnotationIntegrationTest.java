package com.webapp;

import com.webapp.repository.AnnotationRepository;
import com.webapp.repository.entity.AnnotationEntity;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AnnotationIntegrationTest {
    @Autowired
    WebApplicationContext context;

    @Autowired
    AnnotationRepository annotationRepository;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        annotationRepository.save(new AnnotationEntity(
                UUID.fromString("92907c06-f2eb-49b9-9da4-069bed23b3ec"),
                "Java",
                "Read about async java",
                OffsetDateTime.parse("2025-01-01T00:00:00-02:00"))
        );

        annotationRepository.save(new AnnotationEntity(
                UUID.fromString("c44a929f-d124-4884-8d1c-ff151f9723db"),
                "Docker",
                "Read about docker key words",
                OffsetDateTime.parse("2025-01-01T00:00:00-01:00"))
        );
    }

    @AfterEach
    void tearDown() {
        annotationRepository.deleteAll();
    }

    @Test
    @DisplayName("Test System Layers Integration")
    void givenHttpGetRequest_whenGetAllAnnotation_thenReturnOkStatus() throws Exception {
        //arrange
        //act

        //assert
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/annotation"));
        actual.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test System Response Type")
    void givenHttpGetRequest_whenGetAllAnnotation_thenReturnJsonResponseType() throws Exception {
        //arrange
        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/annotation"));

        //assert
        actual.andExpect(content().contentTypeCompatibleWith(MediaType.valueOf("application/hal+json")));
    }

    @Test
    @DisplayName("Test System Get Annotation Response")
    void givenHttpGetRequest_whenGetAllAnnotation_thenReturnCorrectJsonResponse() throws Exception {
        //arrange
        String expectedId1 = "92907c06-f2eb-49b9-9da4-069bed23b3ec";
        String expectedId2 = "c44a929f-d124-4884-8d1c-ff151f9723db";
        String expectedTitle1 = "Java";
        String expectedTitle2 = "Docker";
        String expectedContent1 = "Read about async java";
        String expectedContent2 = "Read about docker key words";
        String expectedCreation1 = "2024-12-31T23:00:00-03:00";
        String expectedCreation2 = "2024-12-31T22:00:00-03:00";

        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/annotation"))
                .andDo(print());

        //assert
        actual.andExpect(jsonPath("$.*.*.[*].id",
                        containsInAnyOrder(expectedId1, expectedId2)))
                .andExpect(jsonPath("$.*.*.[*].title",
                        containsInAnyOrder(expectedTitle1, expectedTitle2)))
                .andExpect(jsonPath("$.*.*.[*].content",
                        containsInAnyOrder(expectedContent1, expectedContent2)))
                .andExpect(jsonPath("$.*.*.[*].creation",
                        containsInAnyOrder(expectedCreation1, expectedCreation2)));
    }

    @Test
    @DisplayName("Test System Get Annotation By Title Response")
    void givenHttpGetRequest_whenGetAnnotationByTitle_thenReturnCorrectJsonResponse() throws Exception {
        //arrange
        String expectedId = "92907c06-f2eb-49b9-9da4-069bed23b3ec";
        String expectedTitle = "Java";
        String expectedContent = "Read about async java";
        String expectedCreation = "2024-12-31T23:00:00-03:00";

        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/annotation/title?title=Java"))
                .andDo(print());

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
    @DisplayName("Test System Post Annotation Response")
    void givenHttpGetRequest_whenPostAnnotation_thenReturnCorrectJsonResponse() throws Exception {
        //arrange
        String expectedTitle = "Spring";
        String expectedContent = "Learn about Spring Boot";

        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.post("/annotation")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"id\": \"\", \"title\": \"Spring\", \"content\": \"Learn about Spring Boot\", \"creation\": \"\"}"))
                .andDo(print());

        //assert
        actual.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(expectedTitle)))
                .andExpect(jsonPath("$.content", is(expectedContent)));
    }
}
