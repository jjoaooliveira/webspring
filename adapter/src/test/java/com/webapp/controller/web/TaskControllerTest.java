package com.webapp.controller.web;

import com.webapp.presenter.TaskPresenter;
import com.webapp.usecase.*;
import com.webapp.usecase.dto.task.InputTaskDTO;
import com.webapp.usecase.dto.task.OutputTaskDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {TaskController.class, TaskPresenter.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    TaskPresenter taskPresenter;

    @MockBean
    SaveUseCase<InputTaskDTO, OutputTaskDTO> mockSaveTaskUseCase;

    @MockBean
    ReadByUseCase<UUID, OutputTaskDTO> mockFindTaskByIdUseCase;

    @MockBean
    ReadAllUseCase<List<OutputTaskDTO>> mockFindAllTaskUseCase;

    @MockBean
    @Qualifier("task")
    DeleteUseCase mockDeleteTaskByIdUseCase;

    @MockBean
    UpdateUseCase<InputTaskDTO, OutputTaskDTO> mockUpdateUseCase;

    @MockBean
    ReadByUseCase<String, List<OutputTaskDTO>> mockFindTaskByTitleUseCase;

    @Test
    @DisplayName("When Get Request to Task Resource Should Return Task DTO List")
    void givenRequestToTaskResource_whenGetAllTask_thenShouldReturnTaskDTOList() throws Exception {
        //arrange
        List<OutputTaskDTO> taskDTOList = List.of(
                new OutputTaskDTO(
                        UUID.randomUUID(),
                        "Title",
                        "Content",
                        OffsetDateTime.now(),
                        OffsetDateTime.now(),
                        "Test",
                        true,
                        true
                ),
                new OutputTaskDTO(
                        UUID.randomUUID(),
                        "Title",
                        "Content",
                        OffsetDateTime.now(),
                        OffsetDateTime.now(),
                        "Test",
                        true,
                        true
                )
        );
        when(mockFindAllTaskUseCase.execute()).thenReturn(taskDTOList);

        //act
        ResultActions actual = mockMvc.perform(MockMvcRequestBuilders.get("/task"));

        //assert
        actual.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.valueOf("application/hal+json")))
                .andExpect(content().string(containsString("Title")))
                .andExpect(content().string(containsString("Content")))
                .andExpect(content().string(containsString("Title")))
                .andExpect(content().string(containsString("Content")));

    }
}
