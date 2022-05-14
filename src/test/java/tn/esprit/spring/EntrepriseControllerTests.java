package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

@SpringBootTest
@AutoConfigureMockMvc
public class EntrepriseControllerTests {
	@Autowired
	private MockMvc mock;
	private Entreprise entreprise = new Entreprise("Vermeg", "Vermeg SAS");
	private Departement departement = new Departement("R&D", entreprise);

	@DisplayName("integration test for ajouterEntreprise method")
	@Test
	void testAjouterEntreprise() throws JsonProcessingException, Exception {
		String entrepriseJson = new ObjectMapper().writeValueAsString(entreprise);
		MvcResult mvcResult = this.mock
				.perform(post("/ajouterEntreprise").contentType("application/json").content(entrepriseJson))
				.andDo(print()).andExpect(status().isOk()).andReturn();
		entreprise.setId(entreprise.getId() + 1);
		String entrepriseId = "" + entreprise.getId();
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		assertThat(actualResponseBody).isEqualToIgnoringWhitespace(entrepriseId);
	}

}