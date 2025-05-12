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
        if (newId < 0) {
            newId = -newId;
        }
        heroToAdd.setId(newId);
        heroes.put(newId, heroToAdd);
        return heroToAdd;
    }

    // Aggiorna un eroe esistente:
    public Optional<Hero> updateHero(Long id, Hero updatedHeroDetails) {
        Hero existingHero = null;
        Long heroKey = null;

        for (Map.Entry<Long, Hero> entry : heroes.entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                existingHero = entry.getValue();
                heroKey = entry.getKey();
                break;
            }
        }

        if (existingHero != null) {
            existingHero.setName(updatedHeroDetails.getName());
            existingHero.setHeroClass(updatedHeroDetails.getHeroClass());
            existingHero.setLevel(updatedHeroDetails.getLevel());
            existingHero.setHealthPoints(updatedHeroDetails.getHealthPoints());
            existingHero.setAttackpower(updatedHeroDetails.getAttackpower());

            heroes.put(heroKey, existingHero);
            return Optional.of(existingHero);
        }

        return Optional.empty();
    }

    // Cancella un eroe esistente:
    public Hero deleteHero(Long id) {
        Iterator<Hero> iterator = heroes.values().iterator();
        Hero heroToDelete = null;

        while (iterator.hasNext()) {
            Hero currentHero = iterator.next();
            if (currentHero.getId().equals(id)) {
                heroToDelete = currentHero;
                iterator.remove();
                break;
            }
        }

        return heroToDelete;
    }

    // Trova gli eroi filtrati per classe:
    public List<Hero> findHeroesByClass(String heroClass) {
        List<Hero> heroesToFindList = new ArrayList<>();

        for (Hero hero : heroes.values()) {
            if (hero.getHeroClass().equals(heroClass)) {
                heroesToFindList.add(hero);
            }
        }

        return heroesToFindList;
    }

    public List<Hero> findHeroesWithHealthAbove(Integer minHealth) {
        List<Hero> heroesToFindList = new ArrayList<>();

        for (Hero hero : heroes.values()) {
            if (hero.getHealthPoints() >= minHealth) {
                heroesToFindList.add(hero);
            }
        }

        return heroesToFindList;
    }
}
