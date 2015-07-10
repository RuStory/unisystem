package dao.jdbc;

import dao.StudentDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Student;
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
public class JDBCStudentDao implements StudentDao {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();


    @Override
    public Boolean createNewStudent(Student student) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("insert into students (name, surname) values (?, ?)");
        prstm.setString(1, student.getName());
        prstm.setString(2, student.getSurname());
        prstm.executeUpdate();
        return true;
    }

    @Override
    public List<Student> getAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select * from students");
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            students.add(new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname")));
        }
        return students;
    }

    @Override
    public Integer getId(Student student) throws SQLException {
        Integer id = 0;
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select id from students where name = ? and surname = ?");
        prstm.setString(1, student.getName());
        prstm.setString(2, student.getSurname());
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }
}
