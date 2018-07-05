import DB.DBConsole;
import DB.DBGame;
import DB.DBHelper;
import models.Console;
import models.Game;
import models.GameType;
import models.Owner;

import java.util.List;

public class Runner {

    public static void main(String[] args) {

        Console console1 = new Console("Sony", "Playstation 4", "Europe");
        DBHelper.save(console1);
        Console console2 = new Console("Microsoft", "XBox 1", "Europe");
        DBHelper.save(console2);

        Game game1 = new Game("Call of Duty", GameType.FPS);
        DBHelper.save(game1);
        Game game2 = new Game("Skyrim", GameType.RPG);
        DBHelper.save(game2);

        Owner owner1 = new Owner("Bob", game2);
        DBHelper.save(owner1);
        Owner owner2 = new Owner("Steve", game2);
        DBHelper.save(owner2);

        List<Console> allConsoles = DBHelper.getAll(Console.class);

        List<Game> allGames = DBHelper.getAll(Game.class);

        DBConsole.addGameToConsole(game1, console1);
        DBConsole.addGameToConsole(game2, console1);
        DBConsole.addGameToConsole(game1, console2);

        List<Game> pS4Games = DBConsole.getConsoleGames(console1);
        List<Game> xBoxGames = DBConsole.getConsoleGames(console2);

        List<Console> codConsoles = DBGame.getGameConsoles(game1);
        List<Console> skyrimConsoles = DBGame.getGameConsoles(game2);

        List<Owner> skyrimOwnerFavs = DBGame.getFavOwners(game2);

        DBConsole.playGame(game1, console1);

//        Game beingPlayed = DBConsole.getGameBeingPlayed(console1);

        Game beingPlayed = DBConsole.getGameBeingPlayed(console1);
    }

}
