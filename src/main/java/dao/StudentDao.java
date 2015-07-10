package dao;

import entities.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public interface StudentDao {
    Boolean createNewStudent(Student student) throws SQLException;
    List<Student> getAll() throws SQLException;
    Integer getId(Student student) throws SQLException;
}
