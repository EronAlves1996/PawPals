package io.eronalves.pawpals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.eronalves.pawpals.controllers.AnimalController;
import io.eronalves.pawpals.entities.Animal;
import io.eronalves.pawpals.entities.AnimalColor;
import io.eronalves.pawpals.entities.AnimalSize;
import io.eronalves.pawpals.entities.AnimalType;
import io.eronalves.pawpals.repositories.AnimalRepository;

@WebMvcTest(value = AnimalController.class)
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

	@Test
	public void testGetAllAnimals() throws JsonProcessingException, Exception {
		Animal animal = new Animal(1, "Dandara", AnimalColor.BLACK, true, AnimalSize.MEDIUM, AnimalType.CAT);
		Animal animal2 = new Animal(2, "Jimbim", AnimalColor.YELLOW, false, AnimalSize.LARGE, AnimalType.DOG);

		List<Animal> animalList = Arrays.asList(animal, animal2);

		when(animalRepository.findAll()).thenReturn(animalList);

		mockMvc.perform(get("/animals/all"))
				.andExpect(status().isOk())
				.andExpect(content().json(om.writeValueAsString(animalList)));
	}

}
