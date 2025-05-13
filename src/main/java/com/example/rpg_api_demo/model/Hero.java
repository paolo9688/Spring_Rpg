package com.example.rpg_api_demo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "heroes")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "class", nullable = false)
    private String heroClass;
    @Column(name = "level", nullable = false)
    private Integer level;
    @Column(name = "health-points", nullable = false)
    private Integer healthPoints;
    @Column(name = "attack-power", nullable = false)
    private Integer attackpower;

    public Hero() {}

    public Hero(String name, String heroClass, Integer level, Integer healthPoints, Integer attackpower) {
        this.name = name;
        this.heroClass = heroClass;
        this.level = level;
        this.healthPoints = healthPoints;
        this.attackpower = attackpower;
    }

    public Hero(Long id, String name, String heroClass, Integer level, Integer healthPoints, Integer attackpower) {
        this.id = id;
        this.name = name;
        this.heroClass = heroClass;
        this.level = level;
        this.healthPoints = healthPoints;
        this.attackpower = attackpower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(Integer healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Integer getAttackpower() {
        return attackpower;
    }

    public void setAttackpower(Integer attackpower) {
        this.attackpower = attackpower;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", heroClass='" + heroClass + '\'' +
                ", level=" + level +
                ", healthPoints=" + healthPoints +
                ", attackpower=" + attackpower +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return Objects.equals(id, hero.id) && Objects.equals(name, hero.name) && Objects.equals(heroClass, hero.heroClass) && Objects.equals(level, hero.level) && Objects.equals(healthPoints, hero.healthPoints) && Objects.equals(attackpower, hero.attackpower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, heroClass, level, healthPoints, attackpower);
    }
}
