package com.example.demo.roulette.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.roulette.model.Bet;
import com.example.demo.roulette.model.Roulette;
import com.example.demo.roulette.repo.RouletteRepository;
import com.example.demo.roulette.service.RouletteServiceImp;

@RestController
@CrossOrigin
@RequestMapping("/rouletters")
public class RouletteController extends BuildGenericResponseController<Roulette> {

		@Autowired
		private RouletteServiceImp rouleteServiceImp;
		private RestResponse restResponse;
	
		@PostMapping
		public ResponseEntity<RestResponse<Roulette>> save() {
			String id = rouleteServiceImp.save();
			return buildResponse(id, null);
		}
	
		@GetMapping
		public List list() {
			return rouleteServiceImp.findAll();
		}

		@SuppressWarnings("unused")
		@PutMapping("/openning/{id}")
		public ResponseEntity<RestResponse<Roulette>> opening(@PathVariable String id) {
			Roulette roulette = null;
			try {
			roulette = rouleteServiceImp.opening(id);
			}catch (Exception e) {
				return buildResponse(e.getMessage(), null);
			}
			
			return buildResponse("La operación fue exitosa", roulette);
		}
		
		@SuppressWarnings("unused")
		@PutMapping("/bet/{idRoulette}")
		public ResponseEntity<RestResponse<Roulette>> createRouletteBet(@PathVariable String idRoulette, @RequestBody Bet bet, @RequestHeader String idUser ) {
			Roulette roulette = null;
			try {
			roulette = rouleteServiceImp.createRouletteBet(idRoulette, bet, idUser);
			}catch (Exception e) {
				return buildResponse(e.getMessage(), null);
			}
			
			return buildResponse("La operación fue exitosa", roulette);
		}
			    
		@SuppressWarnings("unused")
		@PutMapping("/win/{idRoulette}")
		public ResponseEntity<RestResponse<Roulette>> closing(@PathVariable String idRoulette) {
			Roulette roulette = null;
			try {
			roulette = rouleteServiceImp.closing(idRoulette);
			}catch (Exception e) {
				return buildResponse(e.getMessage(), null);
			}
			
			return buildResponse("La operación fue exitosa", roulette);
		}
			    
}
