package dao.jdbc;

import dao.*;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 */
public class JDBCDaoFactory extends DaoFactory {
    @Override
    public LessonDao createLessonDao() {
        return  new JDBCLessonDao();
    }

    @Override
    public StudentDao createStudentDao() {
        return new JDBCStudentDao();
    }

    @Override
    public RoomDao createRoomDao() {
        return new JDBCRoomDao();
    }

    @Override
    public TeacherDao createTeacherDao() {
        return new JDBCTeacherDao();
    }

}
