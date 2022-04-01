package com.controller;

import beans.Ville;
import com.dao.Bdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VilleController {
	// fonction pour récupérer le contenu de la BDD

	@GetMapping("/villes")
	List<Ville> all() {
		List<Ville> villes;
		Bdd bdd = new Bdd();
		villes = bdd.recupererVilles();

		return villes;
	}

	@GetMapping("/villes/{codePostal}")
	public List<Ville> getVillesByCodePostal(@PathVariable String codePostal) {
		List<Ville> villes;
		Bdd bdd = new Bdd();
		villes = bdd.recupererVille(codePostal);

		return villes;
	}
	
	@PostMapping("/villes")
	public String newVille(@RequestBody Ville newVille) {
		Bdd bdd = new Bdd();
		bdd.enregistrerVille(newVille);
		return "Ville ajoutée";
	}

}