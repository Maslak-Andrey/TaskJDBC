package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        //CREATE TABLE users (ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), LASTNAME VARCHAR(20), AGE INT)
        Session session = Util.getSessionFactory().openSession();
        String sql = "CREATE TABLE users (ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), LASTNAME VARCHAR(20), AGE INT)";
        try {
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
        }catch (Throwable e){
            //continue
        }finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        String sql = "DROP TABLE users;";
        try {
            session.createSQLQuery(sql).executeUpdate();
            session.close();
        }catch (Throwable e){
            //continue
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        Session session = Util.getSessionFactory().openSession();
        session.save(user);
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        String sql = "DELETE FROM users WHERE ID= :ID";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.setParameter("ID", id);
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        String sql = "SELECT * FROM users";
        List<User> list = session.createSQLQuery(sql).addEntity(User.class).list();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        String sql = "TRUNCATE TABLE users";
        session.createSQLQuery(sql).executeUpdate();
        session.close();
    }
}
