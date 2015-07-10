package dao.jdbc;

import dao.TeacherDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Teacher;

import java.io.Serializable;
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
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class JDBCTeacherDao implements TeacherDao, Serializable {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();



    @Override
    public List<Teacher> getAll() throws SQLException{
        List<Teacher> teachers = new ArrayList<>();
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select * from teachers");
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            teachers.add(new Teacher(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname")));
        }
        return teachers;
    }

    @Override
    public Boolean createNewTeacher(Teacher teacher) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("insert into teachers (name, surname) values (?, ?)");
        prstm.setString(1, teacher.getName());
        prstm.setString(2, teacher.getSurname());
        prstm.executeUpdate();
        return true;
    }

    @Override
    public Integer getId(Teacher teacher) throws SQLException {
        Integer id = 0;
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select id from teachers where name = ? and surname = ?");
        prstm.setString(1, teacher.getName());
        prstm.setString(2, teacher.getSurname());
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }
}
