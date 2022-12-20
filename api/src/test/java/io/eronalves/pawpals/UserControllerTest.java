package io.eronalves.pawpals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.eronalves.pawpals.entities.AnimalColor;
import io.eronalves.pawpals.entities.AnimalPreferences;
import io.eronalves.pawpals.entities.AnimalSize;
import io.eronalves.pawpals.entities.AnimalType;
import io.eronalves.pawpals.entities.User;
import io.eronalves.pawpals.repositories.UserRepository;

@WebMvcTest
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testRegisterUser() throws JsonProcessingException, Exception {
		User user = new User(1, "Eron", "eron@eron.com", false, null);
		when(userRepository.save(any())).thenReturn(user);

		ObjectMapper om = new ObjectMapper();
		mockMvc
				.perform(post("/users/register")
						.content(om.writeValueAsString(new User(null, "Eron", "eron@eron.com", false, null)))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(om.writeValueAsString(user)));
	}

	@Test
	public void restUpdateAdoptionPreferences() throws JsonProcessingException, Exception {
		AnimalPreferences preferences = new AnimalPreferences(AnimalColor.BLACK, false, AnimalSize.SMALL,
				AnimalType.DOG);
		User user = new User(1, "Eron", "eron@eron.com", true,
				preferences);
		when(userRepository.findById(1))
				.thenReturn(Optional.of(user));

		ObjectMapper om = new ObjectMapper();

		mockMvc
				.perform(post("/users/1/adoption")
						.content(om.writeValueAsString(preferences))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(om.writeValueAsString(user)));
	}

}
