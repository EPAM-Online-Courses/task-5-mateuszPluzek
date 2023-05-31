package efs.task.collections.data;

import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataProvider {

    public static final String DATA_SEPARATOR = ",\\s*";

    // TODO Utwórz listę miast na podstawie tablicy Data.baseTownsArray.
    //  Tablica zawiera String zawierający nazwę miasta oraz dwie klasy bohatera związane z tym miastem oddzielone przecinkami.
    //  Korzystając z funkcji split() oraz stałej DATA_SEPARATOR utwórz listę obiektów klasy efs.task.collections.entities.Town.
    //  Funkcja zwraca listę obiektów typu Town ze wszystkimi dziewięcioma podstawowymi miastami.
    public List<Town> getTownsList() {
        List<Town> listOfTowns = new ArrayList<>();

        for(String line : Data.baseTownsArray) {
            String[] words = line.split(DATA_SEPARATOR);
            List<String> classes = new ArrayList<>();
            classes.add(words[1]);
            classes.add(words[2]);

            listOfTowns.add(new Town(words[0],classes));
        }

        return listOfTowns;
    }

    //TODO Analogicznie do getTownsList utwórz listę miast na podstawie tablicy Data.DLCTownsArray
    public List<Town> getDLCTownsList() {
        List<Town> listOfDLCTowns = new ArrayList<>();

        for(String line : Data.dlcTownsArray) {
            String[] words = line.split(DATA_SEPARATOR);
            List<String> classes = new ArrayList<>();
            classes.add(words[1]);
            classes.add(words[2]);

            listOfDLCTowns.add(new Town(words[0],classes));
        }

        return listOfDLCTowns;
    }

    //TODO Na podstawie tablicy Data.baseCharactersArray utworzyć listę bohaterów dostępnych w grze.
    // Tablica Data.baseCharactersArray zawiera oddzielone przecinkiem imie bohatera, klasę bohatera.
    // Korzystając z funkcji split() oraz DATA_SEPARATOR utwórz listę unikalnych obiektów efs.task.collections.entities.Hero.
    // UWAGA w Data.baseCharactersArray niektórzy bohaterowie powtarzają się, do porównania bohaterów używamy zarówno imie jak i jego klasę;
    public Set<Hero> getHeroesSet() {
        Set<Hero> setOfHeroes = new HashSet<>();

        for(String line : Data.baseCharactersArray) {
            String[] words = line.split(DATA_SEPARATOR);
            Hero hero = new Hero(words[0], words[1]);

            if(setOfHeroes.contains(hero))
                continue;
            else
                setOfHeroes.add(hero);
        }

        return setOfHeroes;
    }

    //TODO Analogicznie do getHeroesSet utwórz listę bohaterów na podstawie tablicy Data.DLCCharactersArray
    public Set<Hero> getDLCHeroesSet() {
        Set<Hero> setOfDLCHeroes = new HashSet<>();

        for(String line : Data.dlcCharactersArray) {
            String[] words = line.split(DATA_SEPARATOR);
            Hero hero = new Hero(words[0], words[1]);

            if(setOfDLCHeroes.contains(hero))
                continue;
            else
                setOfDLCHeroes.add(hero);
        }

        return setOfDLCHeroes;
    }
}
