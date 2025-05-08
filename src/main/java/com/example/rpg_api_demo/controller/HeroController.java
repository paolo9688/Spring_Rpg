package com.example.rpg_api_demo.controller;

import com.example.rpg_api_demo.model.Hero;
import com.example.rpg_api_demo.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroService heroService;

    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/find_all_heroes")
    public ResponseEntity<List<Hero>> getHeroes() {

        List<Hero> heroes = heroService.getAllHeroes();

        if(heroes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(heroes);
    }

    @GetMapping("/find_hero_by_id/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable Long id) {

        Hero hero = heroService.getHeroById(id);

        if(hero == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hero);
    }

    // Aggiungi un nuovo eroe
    @PostMapping("/create_hero")
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {
        Hero heroToAdd = heroService.createHero(hero);
        return ResponseEntity.status(HttpStatus.CREATED).body(heroToAdd);
    }
}
