package DB;

import models.Console;
import models.Game;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBConsole {

    private static Session session;


    public static List<Game> getConsoleGames(Console console){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Game> results = null;
        try {
            Criteria cr = session.createCriteria(Game.class);
            cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            cr.createAlias("consoles", "console");
            cr.add(Restrictions.eq("console.id", console.getId()));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static void addGameToConsole(Game game, Console console){
        game.addConsole(console);
        console.addGame(game);
        DBHelper.update(game);
    }

    public static void playGame(Game game, Console console){
        game.setHostConsole(console);
        console.setGameBeingPlayed(game);
        DBHelper.update(game);
    }

    public static Game getGameBeingPlayed(Console console) {
        session = HibernateUtil.getSessionFactory().openSession();
        Game game = null;
        try {
            Criteria cr = session.createCriteria(Game.class);
            cr.add(Restrictions.eq("id", console.getGameBeingPlayed().getId()));
            game = (Game) cr.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return game;
    }

}



