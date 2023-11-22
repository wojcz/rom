package pl.wojcz.rom.occupancy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.wojcz.rom.occupancy.type.OccupancyUsage;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OccupancyRest.class)
public class OccupancyRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OccupancyService service;

    @Test
    public void checkEndpointShouldReturnOccupancyUsage() throws Exception {
        when(service.check(0, 0)).thenReturn(
                List.of(new OccupancyUsage("Premium"), new OccupancyUsage("Economy"))
        );

        mockMvc.perform(
                post("/occupancy/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"premium\": 0, \"economy\": 0}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("Economy")))
                .andExpect(content().string(containsString("Premium")));
    }

}
