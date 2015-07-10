package dao;

import entities.Room;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public interface RoomDao {
    Boolean createNewRoom(Room room) throws SQLException;
    List<Room> getAll() throws SQLException;
    Integer getId(Room room) throws SQLException;
}
