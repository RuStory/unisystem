package dao.jdbc.transaction;


import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class JDBCTransactionManager implements TransactionManager{
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public Connection getConnection() {
        return connectionHolder.get();
    }

    @Override
    public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {
        InitialContext initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/Park");
        Connection connection = ds.getConnection();
        connection.setAutoCommit(false);
        connectionHolder.set(connection);
        try {
            T result = unitOfWork.call();
            connection.commit();
            return result;
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
        finally {
            connection.close();
            connectionHolder.remove();
        }
    }
}
