package com.controller;

import com.beans.Ville;
import com.dao.Bdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VilleController {
	// fonction pour récupérer le contenu de la BDD
	@Autowired
	private Bdd bdd;

	@GetMapping("/villes")
	List<Ville> all() {
		List<Ville> villes;
		villes = bdd.recupererVilles();

		return villes;
	}

	@GetMapping("/villes/{codePostal}")
	public List<Ville> getVillesByCodePostal(@PathVariable String codePostal) {
		List<Ville> villes;
		villes = bdd.recupererVille(codePostal);

		return villes;
	}
	
	@PostMapping("/villes")
	public String newVille(@RequestBody Ville newVille) {
		bdd.enregistrerVille(newVille);
		return "Ville ajoutée";
	}

	@DeleteMapping("/villes/{codeCommune}")
	public String deleteVille(@PathVariable String codeCommune) {
		bdd.supprimerVille(codeCommune);
		return "Ville supprimée";
	}

	@PutMapping("/villes/{codeCommune}")
	public String updateVille(@PathVariable String codeCommune, @RequestBody Ville newVille) {
		bdd.modifierVille(codeCommune, newVille);
		return "Ville modifiée";
	}

	@PutMapping("/villes/{codeCommune}/{nomColonne}")
	public String udpdateColonneVille(@PathVariable String codeCommune, @PathVariable String nomColonne, @RequestBody String newValue) {
		bdd.modifierColonneVille(codeCommune, nomColonne, newValue);
		return "Ville modifiée";
	}
}