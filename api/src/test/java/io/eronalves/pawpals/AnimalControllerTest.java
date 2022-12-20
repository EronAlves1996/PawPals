package io.eronalves.pawpals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.eronalves.pawpals.entities.Animal;
import io.eronalves.pawpals.entities.AnimalColor;
import io.eronalves.pawpals.entities.AnimalSize;
import io.eronalves.pawpals.entities.AnimalType;
import io.eronalves.pawpals.repositories.AnimalRepository;

@WebMvcTest
public class AnimalControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AnimalRepository animalRepository;

	private ObjectMapper om = new ObjectMapper();

	@Test
	void contextLoads() {
	}

	@Test
	public void testRegisterAnimalForAdoption() throws JsonProcessingException, Exception {
		Animal animal = new Animal(1, "Dandara", AnimalColor.BLACK, true, AnimalSize.MEDIUM, AnimalType.CAT);
		Animal animalTest = new Animal(null, "Dandara", AnimalColor.BLACK, true, AnimalSize.MEDIUM, AnimalType.CAT);
		when(animalRepository.save(any())).thenReturn(animal);

		mockMvc.perform(post("/animals/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(animalTest)))
				.andExpect(status().isOk())
				.andExpect(content().json(om.writeValueAsString(animal)));
	}

}
