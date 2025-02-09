package com.webapp.controller.web;

import com.webapp.presenter.AnnotationPresenter;
import com.webapp.usecase.SimpleInputUseCase;
import com.webapp.usecase.SimpleReturnUseCase;
import com.webapp.usecase.UseCase;
import com.webapp.usecase.dto.annotation.InputAnnotationDTO;
import com.webapp.usecase.dto.annotation.OutputAnnotationDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {AnnotationController.class, AnnotationPresenter.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(AnnotationController.class)
public class AnnotationControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UseCase<InputAnnotationDTO, OutputAnnotationDTO> mockSaveAnnotationUseCase;

    @MockBean
    SimpleReturnUseCase<List<OutputAnnotationDTO>> mockReadAllAnnotationUseCase;

    @MockBean
    SimpleInputUseCase<UUID> mockDeleteAnnotationUseCase;

    @MockBean
    UseCase<UUID, OutputAnnotationDTO> mockFindAnnotationByIdUseCase;

    @MockBean
    UseCase<String, List<OutputAnnotationDTO>> mockFindAnnotationByTitleUseCase;

    @Autowired
    AnnotationPresenter annotationPresenter;

    @Test
    @DisplayName("When Get Request To Annotation Resource Should Return Annotation List")
    void givenRequestToAnnotationResource_whenGetAllAnnotation_thenShouldReturnAnnotationList() throws Exception {
        //arrange
        List<OutputAnnotationDTO> outputAnnotationDTOList = List.of(
                new OutputAnnotationDTO(
                        UUID.randomUUID(),
                        "Title 1",
                        "Content 1",
                        OffsetDateTime.now(ZoneId.of("America/Sao_Paulo"))
                ),
                new OutputAnnotationDTO(
                        UUID.randomUUID(),
                        "Title 2",
                        "Content 2",
                        OffsetDateTime.now(ZoneId.of("America/Sao_Paulo"))
                )
        );
        when(mockReadAllAnnotationUseCase.execute()).thenReturn(outputAnnotationDTOList);

        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/annotation"));

        //assert
        actual.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.valueOf("application/hal+json")))
                .andExpect(content().string(containsString("Title 1")))
                .andExpect(content().string(containsString("Content 1")))
                .andExpect(content().string(containsString("Title 2")))
                .andExpect(content().string(containsString("Content 2")));

    }
}
