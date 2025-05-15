package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.startseva.dtos.CatDto;
import ru.startseva.entities.CatColor;
import ru.startseva.controllers.CatController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ru.startseva.Application;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.MOCK,
  classes = Application.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class PostMethodTest {
    
    @Autowired
    private MockMvc mockMvc;

    private CatController catController;

    private final ObjectWriter ow;

    public static final
    MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    public PostMethodTest() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        this.ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void addCats() throws Exception {

        var firstCat = new CatDto(
                "Katy",
                LocalDate.now(),
                "xz",
                CatColor.White
        );

        var secondCat = new CatDto(
                "Perry",
                LocalDate.now(),
                "xz",
                CatColor.White
        );

        this.mockMvc.perform(
                        post("/api/cats")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(ow.writeValueAsString(firstCat)))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(
                        post("/api/cats")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(ow.writeValueAsString(secondCat)))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
