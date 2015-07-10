package controller.commands;

import controller.ActionCommand;
import dao.DaoFactory;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Lesson;
import entities.Room;
import entities.Student;
import entities.Teacher;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * controller that provides authentication
 * by checking login and password
 */
public class LessonCommand implements ActionCommand {
    private final String PAGE_OK = "/view/redirect.jsp";

    @Override
    public String execute(HttpServletRequest req) throws Exception{

        TransactionManager tx = new JDBCTransactionManager();
        final Teacher newTeacher = new Teacher(req.getParameter("teacher").split(" ")[0], req.getParameter("teacher").split(" ")[1]);
        final Student newStudent = new Student(req.getParameter("student").split(" ")[0], req.getParameter("student").split(" ")[1]);
        final Room newRoom = new Room(Integer.parseInt(req.getParameter("room")));
        String name = req.getParameter("name");
        Time time = Time.valueOf(req.getParameter("time")+":00");

        final Integer currentTeacher = req.getParameter("currentTeacher") == null ? 1 : Integer.parseInt(req.getParameter("currentTeacher"));

        try {
            Callable<Integer> teacherUnit = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return DaoFactory.getInstance().createTeacherDao().getId(newTeacher);
                }
            };
            Integer teacherId = tx.doInTransaction(teacherUnit);
            newTeacher.setId(teacherId);

            Callable<Integer> studentUnit = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return DaoFactory.getInstance().createStudentDao().getId(newStudent);
                }
            };
            final Integer studentId = tx.doInTransaction(studentUnit);
            newStudent.setId(studentId);

            Callable<Integer> roomUnit = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return DaoFactory.getInstance().createRoomDao().getId(newRoom);
                }
            };
            Integer roomId = tx.doInTransaction(roomUnit);
            newRoom.setId(roomId);

            final Lesson newLesson = new Lesson(name, newTeacher, newRoom, time);
            Callable<Integer> lessonUnit = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return DaoFactory.getInstance().createLessonDao().createNewLesson(newLesson);
                }
            };
            final Integer lessonId = tx.doInTransaction(lessonUnit);

            Callable<Boolean> studentOnLessonUnit = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return DaoFactory.getInstance().createLessonDao().createStudentOnLesson(lessonId, studentId);
                }
            };
            tx.doInTransaction(studentOnLessonUnit);


            req.setAttribute("currentTeacher", currentTeacher);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some problems in teacher controller");
            throw e;
        }
        return PAGE_OK;
    }
}
