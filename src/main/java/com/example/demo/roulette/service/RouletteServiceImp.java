package com.example.demo.roulette.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.roulette.model.Bet;
import com.example.demo.roulette.model.Roulette;
import com.example.demo.roulette.repo.RouletteRepository;
import com.example.demo.user.model.User;
import com.example.demo.user.repo.UserRepository;

@Service
public class RouletteServiceImp {

	@Autowired
	private RouletteRepository rouletteRepository;
	@Autowired
	private UserRepository userRepository;

	private static final String ROJO = "rojo";
	private static final String NEGRO = "negro";

	public String save() {
		Roulette roulette = new Roulette();
		roulette.setStatus(false);
		roulette.setId(UUID.randomUUID().toString().replace("-", ""));
		return rouletteRepository.save(roulette);
	}

	public List<Roulette> findAll() {
		return rouletteRepository.findAll();
	}

	public Roulette opening(String id) throws Exception {
		Roulette roulette = rouletteRepository.findById(id);
		if (id == null)
			throw new Exception("empty");
		if (roulette == null)
			throw new Exception("not exist roulette");
		if (roulette.getStatus() == true)
			throw new Exception("Ya estaba activa");
		if (!roulette.getBet().isEmpty())
			roulette.getBet().clear();
		roulette.setStatus(true);
		rouletteRepository.update(roulette);
		return roulette;
	}

	public Roulette closing(String id) throws Exception {
		Roulette roulette = rouletteRepository.findById(id);
		if (id == null)
			throw new Exception("empty");
		if (roulette == null)
			throw new Exception("not exist roulette");
		if (roulette.getStatus() == false)
			throw new Exception("Ya estaba cerrada");
		roulette = winBet(roulette);
		roulette.setStatus(false);
		rouletteRepository.update(roulette);
		return roulette;
	}

	public Roulette winBet(Roulette roulette) {
		Random random = new Random();
		int winNumber = random.nextInt(37);
		for (Bet bet : roulette.getBet()) {
			if (isNumeric(bet.getValue())) {
				if (Integer.parseInt(bet.getValue()) == winNumber) {
					bet.getUser().setBalance(bet.getCash() + bet.getCash() * 5);
				}
			} else {
				if (winNumber != 0) {
					bet.getUser()
							.setBalance((balanceColor(winNumber).equalsIgnoreCase(bet.getValue())
									? bet.getCash() + bet.getCash() * 1.8
									: bet.getCash()));
				}
			}
		}
		return roulette;
	}

	public String balanceColor(int number) {
		if (number % 2 == 0) {
			return ROJO;
		} else {
			return NEGRO;
		}
	}

	public Roulette createRouletteBet(String idRoulette, Bet bet, String idUser) throws Exception {
		Roulette roulette = rouletteRepository.findById(idRoulette);
		User user = userRepository.findById(idUser);
		validator(roulette, bet, user);
		user.setBalance(user.getBalance() - bet.getCash());
		bet.setUser(user);
		roulette.getBet().add(bet);
		userRepository.update(user);
		rouletteRepository.update(roulette);
		return roulette;
	}

	public void validator(Roulette roulette, Bet bet, User user) throws Exception {
		if (user == null)
			throw new Exception("No se ha encontrado el usuario");
		if (roulette == null)
			throw new Exception("No se ha encontrado la ruleta");
		if (roulette.getStatus().equals(Boolean.FALSE))
			throw new Exception("La ruleta está cerrada");
		if (user.getBalance() < bet.getCash())
			throw new Exception("El usuario no posee fondo suficiente para apostar");
		if (!(bet.getCash() >= 1 && bet.getCash() <= 10000))
			throw new Exception("El rango en dinero para apuesta no está en el rango (entre 0 y 10000)");
		if (!validatorBuildBet(bet))
			throw new Exception("Datos erroneos");
	}

	public boolean validatorBuildBet(Bet bet) throws Exception {
		int value = -1;
		if (isNumeric(bet.getValue())) {
			value = Integer.parseInt(bet.getValue());
			return value >= 0 && value <= 36;
		}
		return bet.getValue().equalsIgnoreCase(NEGRO) || bet.getValue().equalsIgnoreCase(ROJO);

	}

	private boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
