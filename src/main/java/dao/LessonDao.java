package dao;

import entities.Lesson;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
public interface LessonDao {
    List<Lesson> getLessonsByTeacher(Integer id) throws SQLException;
    Integer createNewLesson(Lesson lesson) throws SQLException;
    Boolean createStudentOnLesson(Integer lessonId, Integer studentId) throws SQLException;
}
