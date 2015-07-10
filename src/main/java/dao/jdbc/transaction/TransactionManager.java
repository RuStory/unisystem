package dao.jdbc.transaction;

import java.sql.Connection;
import java.util.concurrent.Callable;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public interface TransactionManager {
    public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception;
    public Connection getConnection();
}
