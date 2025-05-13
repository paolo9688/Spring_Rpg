package com.example.rpg_api_demo.repository;

import com.example.rpg_api_demo.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findByHeroClass(String heroClass);
    List<Hero> findByHealthPointsGreaterThan(Integer minHealthPoints);
    List<Hero> findAllByOrderByLevelDesc();
}