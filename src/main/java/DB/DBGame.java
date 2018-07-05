package DB;

import models.Console;
import models.Game;
import models.Owner;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBGame {

    private static Session session;


    public static List<Console> getGameConsoles(Game game){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Console> results = null;
        try {
            Criteria cr = session.createCriteria(Console.class);
            cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            cr.createAlias("games", "game");
            cr.add(Restrictions.eq("game.id", game.getId()));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static List<Owner> getFavOwners(Game game) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Owner> results = null;
        try {
            Criteria cr = session.createCriteria(Owner.class);
            cr.add(Restrictions.eq("favouriteGame", game));
            results =  cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static Game getGameBeingPlayed(Game game) {
        session = HibernateUtil.getSessionFactory().openSession();
        Game result = null;
        try {
            Criteria cr = session.createCriteria(Console.class);
            cr.add(Restrictions.eq("game_id", game.getId()));
            result = (Game) cr.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}



