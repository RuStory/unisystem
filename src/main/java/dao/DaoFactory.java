package dao;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 05.05.15
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class DaoFactory {
    public abstract LessonDao createLessonDao();
    public abstract StudentDao createStudentDao();
    public abstract RoomDao createRoomDao();
    public abstract TeacherDao createTeacherDao();


    public static DaoFactory getInstance() {
        try {
            /*Properties config = new Properties();
            config.load(new FileInputStream("/dao.properties"));
            return (DaoFactory) Class.forName(config.getProperty("dao.factory")).newInstance();*/
            return (DaoFactory) Class.forName("dao.jdbc.JDBCDaoFactory").newInstance();
        } catch (Exception ex) {
            return null;
        }
    }
}
