package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.hero.features.connectors.HeroPowerStatsConnector;
import br.com.gubee.interview.core.hero.hero.HeroController;
import br.com.gubee.interview.core.hero.hero.HeroService;
import br.com.gubee.interview.model.enums.Race;
import br.com.gubee.interview.model.request.CreateHeroRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HeroController.class)
class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HeroService heroService;

    @MockBean
    private HeroPowerStatsConnector heroPowerStatsConnector;

    HeroRepositoryStub heroRepositoryStub;


    @BeforeEach
    public void initTest() {
        when(heroPowerStatsConnector.createHero(any())).thenReturn(UUID.randomUUID());
    }

    @Test
    void createAHeroWithAllRequiredArguments() throws Exception {
        //given
        // Convert the hero request into a string JSON format stub.
        final String body = objectMapper.writeValueAsString(createHeroRequest());

        //when
        final ResultActions resultActions = mockMvc.perform(post("/api/v1/heroes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(body));

        //then
        resultActions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        verify(heroPowerStatsConnector, times(1)).createHero(any());
    }

    private CreateHeroRequest createHeroRequest() {
        return CreateHeroRequest.builder()
            .name("Batman")
            .agility(5)
            .dexterity(8)
            .strength(6)
            .intelligence(10)
            .race(Race.HUMAN)
            .build();
    }
}