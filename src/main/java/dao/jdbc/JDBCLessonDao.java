package dao.jdbc;

import com.mysql.jdbc.Statement;
import dao.LessonDao;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Lesson;
import entities.Room;
import entities.Student;
import entities.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class JDBCLessonDao implements LessonDao {
    private Connection connection;
    private TransactionManager dataSource = new JDBCTransactionManager();

    @Override
    public List<Lesson> getLessonsByTeacher(Integer id) throws SQLException {
        Map<Integer, Lesson> lessons = new HashMap<>();
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("select l.id, l.name as lesson_name, l.time, t.id as teacher_id, t.name, t.surname, r.id as room_id, r.number from lessons l \n" +
                "join\n" +
                "teachers t on l.teacher_id = t.id\n" +
                "join\n" +
                "rooms r on l.room_id = r.id\n" +
                "where l.teacher_id = ?");
        prstm.setInt(1, id);
        ResultSet resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            lessons.put(resultSet.getInt("id"),
                    new Lesson(
                            resultSet.getInt("id"),
                            resultSet.getString("lesson_name"),
                            new Teacher(resultSet.getInt("teacher_id"), resultSet.getString("name"), resultSet.getString("surname")),
                            new Room(resultSet.getInt("room_id"), resultSet.getInt("number")),
                            resultSet.getTime("time")
                    ));
        }

        prstm = connection.prepareStatement("select l.id as lesson_id, s.id as student_id, s.name, s.surname from\n" +
                "teachers t \n" +
                "join\n" +
                "lessons l on t.id = l.teacher_id\n" +
                "join \n" +
                "student_lesson sl on sl.lesson_id = l.id\n" +
                "join\n" +
                "students s on s.id = sl.student_id\n" +
                "where t.id = ?");
        prstm.setInt(1, id);
        resultSet = prstm.executeQuery();

        while (resultSet.next()) {
            lessons.get(resultSet.getInt("lesson_id"))
                    .getStudents()
                    .add(new Student(
                            resultSet.getInt("student_id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname")
                    ));
        }
        return new ArrayList<>(lessons.values());
    }

    @Override
    public Integer createNewLesson(Lesson lesson) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("insert into lessons (teacher_id, room_id, name, time) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        prstm.setInt(1, lesson.getTeacher().getId());
        prstm.setInt(2, lesson.getRoom().getId());
        prstm.setString(3, lesson.getName());
        prstm.setTime(4, lesson.getTime());
        prstm.executeUpdate();
        ResultSet keys = prstm.getGeneratedKeys();
        keys.next();
        Integer key = keys.getInt(1);
        return key;
    }

    @Override
    public Boolean createStudentOnLesson(Integer lessonId, Integer studentId) throws SQLException {
        connection = dataSource.getConnection();
        PreparedStatement prstm = connection.prepareStatement("insert into student_lesson (student_id, lesson_id) values (?, ?)");
        prstm.setInt(1, studentId);
        prstm.setInt(2, lessonId);
        prstm.executeUpdate();

        return true;
    }
}
