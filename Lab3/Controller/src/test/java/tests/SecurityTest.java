package tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.startseva.Application;
import ru.startseva.dtos.OwnerDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@AutoConfigureMockMvc
public class SecurityTest {
  public static final MediaType APPLICATION_JSON_UTF8 =
      new MediaType(
          MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          StandardCharsets.UTF_8);

  @Autowired private MockMvc mockMvc;

  private final ObjectWriter ow;

  public SecurityTest() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.findAndRegisterModules();
    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    this.ow = mapper.writer().withDefaultPrettyPrinter();
  }

  @Test
  public void testRegistrationUnauthorized() throws Exception {
    this.mockMvc.perform(post("/api/users/registration")).andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser(roles = {"ADMIN"})
  public void testAddingOwner() throws Exception {
    OwnerDto ownerDto = new OwnerDto("Arina", LocalDate.now());
    this.mockMvc
        .perform(
            post("/api/owners")
                .contentType(APPLICATION_JSON_UTF8)
                .content(ow.writeValueAsString(ownerDto)))
        .andExpect(status().isOk());
  }
}
