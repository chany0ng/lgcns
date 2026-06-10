package com.lgcns.jpadsl.post;

import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {
    private static final PostSaveDTO saveDto = PostSaveDTO.builder()
            .title("title")
            .writer("writer")
            .body("body")
            .build();
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;  // json test

    @Test
    void createPostTest() throws Exception {
        String uri = "/api/posts";

        mockMvc.perform(
                        post(uri)
                                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                                .content(objectMapper.writeValueAsString(saveDto))
                ).andExpect(status().isOk())
                .andExpect(content().contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.writer").value("writer"))
                .andDo(print());
    }
}
