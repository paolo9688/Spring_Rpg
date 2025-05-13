package com.example.rpg_api_demo.controller;

import com.example.rpg_api_demo.model.Hero;
import com.example.rpg_api_demo.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    private final HeroService heroService;

    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    // Trova tutti gli eroi:
    @GetMapping("/find_all_heroes")
    public ResponseEntity<List<Hero>> getHeroes() {

        List<Hero> heroes = heroService.getAllHeroes();

        if (heroes == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(heroes);
    }

    // Aggiungi un nuovo eroe:
    @PostMapping("/create_hero")
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {
        Hero heroToAdd = heroService.createHero(hero);
        return ResponseEntity.ok(heroToAdd);
    }

    // Trova eroe per id:
    @GetMapping("/find_hero_by_id/{id}")
    public ResponseEntity<Optional<Hero>> getHeroById(@PathVariable Long id) {

        Optional<Hero> hero = heroService.getHeroById(id);

        if(hero == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hero);
    }

    // Aggiorna un eroe esistente:
    @PutMapping("/update_hero/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable Long id, @RequestBody Hero heroDetails) {
        Optional<Hero> heroToUpdate = heroService.updateHero(id, heroDetails);

        if (heroToUpdate.isPresent()) {
            return ResponseEntity.ok(heroToUpdate.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Cancella un eroe esistente:
    @DeleteMapping("/delete_hero/{id}")
    public ResponseEntity<Optional<Hero>> deleteHero(@PathVariable Long id) {
        Optional<Hero> heroToDelete = heroService.deleteHero(id);

        if (heroToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(heroToDelete);
    }

    // Ritorna un eroe per classe di appartenenza:
    @GetMapping("/class/{className}")
    public ResponseEntity<List<Hero>> getHeroesByClass(@PathVariable String className) {
        List<Hero> heroToFind = heroService.findHeroesByClass(className);

        if (heroToFind == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(heroToFind);
    }

    // Ritorna tutti gli eroi con salute maggiore di un certo valore minimo:
    @GetMapping("/health-above")
    public ResponseEntity<List<Hero>> getHeroesWithHealthAbove(@RequestParam Integer minHealth) {
        List<Hero> heroes = heroService.findHeroesWithHealthAbove(minHealth);

        if (heroes == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(heroes);
    }

    // Ritorna tutti gli eroi filtrati per livello decrescente:
    @GetMapping("/sorted/level-desc")
    public ResponseEntity<List<Hero>> getAllHeroesSortedByLevelDesc() {
        List<Hero> heroes = heroService.getAllHeroesSortedByLevelDesc();

        if (heroes == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(heroes);
    }
}
