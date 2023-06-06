package efs.task.collections.game;

import efs.task.collections.data.DataProvider;
import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.*;
//
public class GameLobby {

    public static final String HERO_NOT_FOUND = "Nie ma takiego bohatera ";
    public static final String NO_SUCH_TOWN = "Nie ma takiego miasta ";

    private final DataProvider dataProvider;
    private Map<Town, List<Hero>> playableTownsWithHeroesList;

    public GameLobby() {
        this.dataProvider = new DataProvider();
        this.playableTownsWithHeroesList =
                mapHeroesToStartingTowns(dataProvider.getTownsList(), dataProvider.getHeroesSet());
    }

    public Map<Town, List<Hero>> getPlayableTownsWithHeroesList() {
        return playableTownsWithHeroesList;
    }

    //TODO Dodać miasta i odpowiadających im bohaterów z DLC gry do mapy dostępnych
    // miast - playableTownsWithHeroesList, tylko jeżeli jeszcze się na niej nie znajdują.
    public void enableDLC() {
        List<Town> townDLCList = dataProvider.getDLCTownsList();
        Set<Hero> heroDLCList = dataProvider.getDLCHeroesSet();
        List<Hero> correctHeroes;

        for(Town dlcTown: townDLCList) {
            if(playableTownsWithHeroesList.containsKey(dlcTown))
                continue;

            correctHeroes = new ArrayList<>();
            for(Hero dlcHero: heroDLCList) {
                if(dlcTown.getStartingHeroClasses().contains(dlcHero.getHeroClass())) {
                    correctHeroes.add(dlcHero);
                }
            }
            playableTownsWithHeroesList.put(dlcTown, correctHeroes);
        }
    }


    //TODO Usunąć miasta i odpowiadających im bohaterów z DLC gry z mapy dostępnych
    // miast - playableTownsWithHeroesList.
    public void disableDLC() {
        List<Town> townDLCList = dataProvider.getDLCTownsList();

        for(Town dlcTown: townDLCList) {
            if(playableTownsWithHeroesList.containsKey(dlcTown))
                playableTownsWithHeroesList.remove(dlcTown);
        }
    }

    // TODO Sprawdza czy mapa playableCharactersByTown zawiera dane miasto.
    //  Jeśli tak zwróć listę bohaterów z tego miasta.
    //  Jeśli nie rzuć wyjątek NoSuchElementException z wiadomością NO_SUCH_TOWN + town.getName()
    public List<Hero> getHeroesFromTown(Town town) {
        if(playableTownsWithHeroesList.containsKey(town)) {
            return playableTownsWithHeroesList.get(town);
        }
        else {
            throw new NoSuchElementException(NO_SUCH_TOWN + town.getTownName());
        }
    }

    // TODO Metoda powinna zwracać mapę miast w kolejności alfabetycznej z odpowiadającymi im bohaterami.
    //  Każde z miast charakteryzuje się dwoma klasami bohaterów dostępnymi dla tego miasta - Town.startingHeroClass.
    //  Mapa ma zawierać pare klucz-wartość gdzie klucz: miasto, wartość: lista bohaterów;
    public Map<Town, List<Hero>> mapHeroesToStartingTowns(List<Town> availableTowns, Set<Hero> availableHeroes) {
        List<Hero> correctHeroes;
        Map<Town, List<Hero>> mappedHeroes = new TreeMap<>();

        for(Town town: availableTowns) {
            if(mappedHeroes.containsKey(town))
                continue;

            correctHeroes = new ArrayList<>();
            for(Hero hero: availableHeroes) {
                if(town.getStartingHeroClasses().contains(hero.getHeroClass())) {
                    correctHeroes.add(hero);
                }
            }
            mappedHeroes.put(town, correctHeroes);
        }
        return mappedHeroes;
    }

    //TODO metoda zwraca wybranego bohatera na podstawie miasta z którego pochodzi i imienia.
    // Jeżeli istnieje usuwa go z listy dostępnych bohaterów w danym mieście i zwraca bohatera.
    // Jeżeli nie ma go na liście dostępnych bohaterów rzuca NoSuchElementException z wiadomością HERO_NOT_FOUND + name
    public Hero selectHeroByName(Town heroTown, String name) {
        Hero hero;
        List<Hero> tmp = playableTownsWithHeroesList.get(heroTown);

        for(Hero h: tmp) {
            if(h.getName().equals(name)){
                hero = h;
                tmp.remove(h);
                playableTownsWithHeroesList.replace(heroTown, tmp);
                return hero;
            }
        }
        throw new NoSuchElementException(HERO_NOT_FOUND + name);
    }
}