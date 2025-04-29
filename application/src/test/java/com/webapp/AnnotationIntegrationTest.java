package com.webapp;

import com.webapp.entity.Annotation;
import com.webapp.entity.Content;
import com.webapp.entity.Title;
import com.webapp.usecase.annotation.AnnotationDataGateway;
import org.junit.jupiter.api.*;
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
    AnnotationDataGateway dataGateway;

    MockMvc mockMvc;

    Annotation annotation1, annotation2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        annotation1 = dataGateway.save(new Annotation(
                new Title("Java"),
                new Content("Read about async java"))
        );

        annotation2 = dataGateway.save(new Annotation(
                new Title("Docker"),
                new Content("Read about docker key words"))
        );
    }

    @AfterEach
    void tearDown() {
        dataGateway.delete(annotation1.getId());
        dataGateway.delete(annotation2.getId());
    }

    @Test
    @DisplayName("Test Get All Annotation")
    void givenHttpGetRequest_whenGetAllAnnotation_thenReturnCorrectJsonResponse() throws Exception {
        //arrange
        String expectedId1 = annotation1.getId().toString();
        String expectedId2 = annotation2.getId().toString();
        String expectedTitle1 = annotation1.getTitle();
        String expectedTitle2 = annotation2.getTitle();
        String expectedContent1 = annotation1.getContent();
        String expectedContent2 = annotation2.getContent();


        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/annotation"))
                .andDo(print());

        //assert
        actual.andExpect(jsonPath("$.*.*.[*].id",
                        containsInAnyOrder(expectedId1, expectedId2)))
                .andExpect(jsonPath("$.*.*.[*].title",
                        containsInAnyOrder(expectedTitle1, expectedTitle2)))
                .andExpect(jsonPath("$.*.*.[*].content",
                        containsInAnyOrder(expectedContent1, expectedContent2)));
    }

    @Test
    @DisplayName("Test Get Annotation By Title")
    void givenHttpGetRequest_whenGetAnnotationByTitle_thenReturnCorrectJsonResponse() throws Exception {
        //arrange
        String expectedId = annotation1.getId().toString();
        String expectedTitle = annotation1.getTitle();
        String expectedContent = annotation1.getContent();

        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/annotation/title?title=Java"))
                .andDo(print());

        //assert
        actual.andExpect(jsonPath("$.*.*.[*].id",
                        containsInAnyOrder(expectedId)))
                .andExpect(jsonPath("$.*.*.[*].title",
                        containsInAnyOrder(expectedTitle)))
                .andExpect(jsonPath("$.*.*.[*].content",
                        containsInAnyOrder(expectedContent)));
    }

    @Test
    @DisplayName("Test Post Annotation")
    void givenHttpGetRequest_whenPostAnnotation_thenReturnCorrectJsonResponse() throws Exception {
        //arrange
        String expectedTitle = "Spring";
        String expectedContent = "Learn about Spring Boot";

        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.post("/annotation")
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"title\": \"Spring\", \"content\": \"Learn about Spring Boot\"}"))
                .andDo(print());

        //assert
        actual.andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(expectedTitle)))
                .andExpect(jsonPath("$.content", is(expectedContent)));
    }
}
