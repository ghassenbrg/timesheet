package tn.esprit.spring;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.text.ParseException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.EntrepriseServiceImpl;

@ExtendWith(MockitoExtension.class)
class EntrepriseServiceTests {

	@Mock
	private EntrepriseRepository entrepriseRepository;

	@Mock
	private DepartementRepository departementRepository;

	@InjectMocks
	private EntrepriseServiceImpl entrepriseService;

	private Entreprise entreprise;
	private Departement departement;

	@BeforeEach
	private void init() throws ParseException {
		entreprise = new Entreprise("Vermeg", "Vermeg SAS");
		entreprise.setId(new Random().nextInt() & Integer.MAX_VALUE);
		departement = new Departement("R&D", entreprise);
		departement.setId(new Random().nextInt() & Integer.MAX_VALUE);
	}

	@DisplayName("JUnit test for ajouterEntreprise method")
	@Test
	void testAjouterEntreprise() {
		given(entrepriseRepository.save(entreprise)).willReturn(entreprise);
		int idSavedEntreprise = entrepriseService.ajouterEntreprise(entreprise);
		assertThat(idSavedEntreprise).isPositive();
	}

	@DisplayName("JUnit test for ajouterDepartement method")
	@Test
	void testAjouterDepartement() {
		given(departementRepository.save(departement)).willReturn(departement);
		int idSavedDepartement = entrepriseService.ajouterDepartement(departement);
		assertThat(idSavedDepartement).isPositive();
	}

}
