package com.example.demo.roulette.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.roulette.model.Bet;
import com.example.demo.roulette.model.Roulette;
import com.example.demo.user.model.User;
import com.example.demo.user.repo.UserRepository;


@Repository
public class RouletteRepository {
	public static final String ROULETTE_KEY = "ROULETTE";

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    public RouletteRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public String save(Roulette roulette) {
        hashOperations.put(ROULETTE_KEY, roulette.getId(), roulette);
        return roulette.getId();
    }

    public List findAll(){
        return hashOperations.values(ROULETTE_KEY);
    }

    public Roulette findById(String id) {
        return (Roulette) hashOperations.get(ROULETTE_KEY, id);
    }

    public void update(Roulette roulette) {
        save(roulette);
    }

    public void delete(String id) {
        hashOperations.delete(ROULETTE_KEY, id);
    }
    
}
