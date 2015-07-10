package dao;

import entities.Teacher;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public interface TeacherDao {
    List<Teacher> getAll() throws SQLException;
    Boolean createNewTeacher(Teacher teacher) throws SQLException;
    Integer getId(Teacher teacher) throws SQLException;
}
