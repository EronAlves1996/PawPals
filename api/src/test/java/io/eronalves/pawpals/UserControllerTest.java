package io.eronalves.pawpals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.print.attribute.standard.Media;
import javax.swing.plaf.metal.MetalBorders.MenuItemBorder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public void registerUser() throws JsonProcessingException, Exception {
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

}
