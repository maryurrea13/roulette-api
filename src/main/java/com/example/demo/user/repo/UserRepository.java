package com.example.demo.user.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.user.model.User;

@Repository
public class UserRepository {

    public static final String USER_KEY = "USER";

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public UserRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(User user) {
    	user.setId(UUID.randomUUID().toString().replace("-", ""));
        hashOperations.put(USER_KEY, user.getId(), user);
    }

    public List findAll(){
        return hashOperations.values(USER_KEY);
    }

    public User findById(String id) {
        return (User) hashOperations.get(USER_KEY, id);
    }

    public void update(User user) {
        save(user);
    }

    public void delete(String id) {
        hashOperations.delete(USER_KEY, id);
    }

}