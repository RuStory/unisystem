package dao.jdbc;


import dao.RoomDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Room;
import entities.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class JDBCRoomDao implements RoomDao {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();


    @Override
    public Boolean createNewRoom(Room room) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("insert into rooms (number) values (?)");
        prstm.setInt(1, room.getNumber());
        prstm.executeUpdate();
        return true;
    }

    @Override
    public List<Room> getAll() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select * from rooms");
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            rooms.add(new Room(resultSet.getInt("id"), resultSet.getInt("number")));
        }
        return rooms;
    }

    @Override
    public Integer getId(Room room) throws SQLException {
        Integer id = 0;
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select id from rooms where number = ?");
        prstm.setInt(1, room.getNumber());
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }
}
