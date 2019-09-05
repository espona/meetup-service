package es.backend.meetup.service.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MeetupServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void nearGroupsShouldReturnNumResults() throws Exception {

        this.mockMvc.perform(get("/meetup/near").param("lat", "43.42").param("lon", "-3.71").param("num", "5"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.num").value("5"));
    }

    @Test
    public void topCitiesShouldReturnNumResults() throws Exception {

        this.mockMvc.perform(get("/meetup/topCities").param("date", "2019-09-04").param("num", "5"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.num").value("5"));
    }
}
