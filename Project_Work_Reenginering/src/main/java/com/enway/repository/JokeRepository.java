package com.enway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enway.entity.Joke;

public interface JokeRepository extends JpaRepository<Joke, Integer>{

}
