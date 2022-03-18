package com.controller;

import com.dao.Bdd;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class VilleController {

	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	public ArrayList<String> get(@RequestParam(required  = false, value="codePostal") String codePostal) {
		System.out.println("get");
		ArrayList<String> villes = new ArrayList<String>();

		Bdd bdd = new Bdd();
		villes = bdd.recupererVille(codePostal);

		return villes;
	}
	
	@RequestMapping(value="/villeEntree", method=RequestMethod.GET)
	public String get(@RequestParam(required  = true, value="nomCommune") String nomCommune,
								 @RequestParam(required  = true, value="codePostal") String codePostal) {

		System.out.println("get");
		ArrayList<String> villes = new ArrayList<String>();

		Bdd bdd = new Bdd();
		bdd.enregistrerVille(nomCommune, codePostal);

		return "ok";
	}

}