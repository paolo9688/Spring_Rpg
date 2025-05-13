package com.example.rpg_api_demo.service;

import com.example.rpg_api_demo.model.Hero;
import com.example.rpg_api_demo.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HeroService {
    // N.B. La mappa mi serviva prima quando non ero ancora connesso con il database
    // Ora che sono connesso uso heroRepository con i suoi metodi per tutte le operazioni

    /*private final Map<Long, Hero> heroes = new HashMap<Long, Hero>() {{
        put(1L, new Hero(1L, "Aragorn", "Ranger", 85, 2000, 150));
        put(2L, new Hero(50L, "Pikachu", "Elf", 90, 500, 50));
        put(3L, new Hero(8L, "Ash", "Imperial", 11, 1000, 100));
    }};*/

    @Autowired
    private HeroRepository heroRepository;

    private final Long idCounter = 1L;

    // Ritorna tutti gli eroi:
    public List<Hero> getAllHeroes() {
        List<Hero> heroList = heroRepository.findAll();
        return heroList;
    }

    // Crea un nuovo eroe:
    public Hero createHero(Hero hero) {
        return heroRepository.save(hero);
    }

    // Ritorna l'eroe per id:
    public Optional<Hero> getHeroById(Long id) {
        Optional<Hero> optionalHero = heroRepository.findById(id);
        return optionalHero;
    }

    // Aggiorna un eroe esistente:
    public Optional<Hero> updateHero(Long id, Hero updatedHeroDetails) {
        Optional<Hero> heroOptional = heroRepository.findById(id);
        if (heroOptional.isPresent()){
            heroOptional.get().setName(updatedHeroDetails.getName());
            heroOptional.get().setHeroClass(updatedHeroDetails.getHeroClass());
            heroOptional.get().setLevel(updatedHeroDetails.getLevel());
            heroOptional.get().setHealthPoints(updatedHeroDetails.getHealthPoints());
            heroOptional.get().setAttackpower(updatedHeroDetails.getAttackpower());
            Hero user = heroRepository.save(heroOptional.get());
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    // Cancella un eroe esistente:
    public Optional<Hero> deleteHero(Long id) {
        Optional<Hero> heroOptional = heroRepository.findById(id);
        if (heroOptional.isPresent()) {
            heroRepository.deleteById(id);
            return heroOptional;
        } else {
            return Optional.empty();
        }
    }

    // Trova gli eroi filtrati per classe:
    public List<Hero> findHeroesByClass(String heroClass) {
        return heroRepository.findByHeroClass(heroClass);
    }

    // Trova gli eroi con vita maggiore di un certo livello:
    public List<Hero> findHeroesWithHealthAbove(Integer minHealth) {
        return heroRepository.findByHealthPointsGreaterThan(minHealth);
    }

    // Ritorna tutti gli eroi ordinati per livello decrescente:
    public List<Hero> getAllHeroesSortedByLevelDesc() {
        return heroRepository.findAllByOrderByLevelDesc();
    }
}
