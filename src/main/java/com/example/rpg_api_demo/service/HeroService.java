package com.example.rpg_api_demo.service;

import com.example.rpg_api_demo.model.Hero;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HeroService {
    private final Map<Long, Hero> heroes = new HashMap<Long, Hero>() {{
        put(1L, new Hero(1L, "Aragorn", "Ranger", 85, 2000, 150));
        put(2L, new Hero(50L, "Pikachu", "Elf", 50, 500, 50));
        put(3L, new Hero(8L, "Ash", "Imperial", 11, 1000, 100));
    }};

    private final Long idCounter = 1L;

    // Ritorna tutti gli eroi:
    public List<Hero> getAllHeroes() {
        List<Hero> heroList = new ArrayList<>(heroes.values());
        return heroList;
    }

    // Ritorna l'eroe per id:
    public Hero getHeroById(Long id) {
        Hero heroToFind = null;
        for (Hero hero : heroes.values()) {
            if (hero.getId().equals(id)) {
                heroToFind = hero;
            }
        }

        return heroToFind;
    }

    // Crea un nuovo eroe:
    public Hero createHero(Hero hero) {
        Hero heroToAdd = hero;
        Long newId = new Random().nextLong();
        heroToAdd.setId(newId);
        heroes.put(newId, heroToAdd);
        return heroToAdd;
    }
}
