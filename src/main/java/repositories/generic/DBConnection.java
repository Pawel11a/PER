package repositories.generic;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {

    private static DBConnection dbConnection = new DBConnection();
    public static DBConnection getDbConnection() {
        return dbConnection;
    }

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PER");
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
