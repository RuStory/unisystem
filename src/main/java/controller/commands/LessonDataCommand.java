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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * controller that provides authentication
 * by checking login and password
 */
public class LessonDataCommand implements ActionCommand {
    private final String PAGE_OK = "/view/lesson.jsp";

    @Override
    public String execute(HttpServletRequest req) throws Exception{

        TransactionManager tx = new JDBCTransactionManager();

        final Integer currentTeacher = req.getParameter("currentTeacher") == null ? 1 : Integer.parseInt(req.getParameter("currentTeacher"));
        List<Teacher> teachers = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();

        try {
            Callable<List<Teacher>> teacherUnit = new Callable<List<Teacher>>() {
                @Override
                public List<Teacher> call() throws Exception {
                    return DaoFactory.getInstance().createTeacherDao().getAll();
                }
            };
            teachers = tx.doInTransaction(teacherUnit);

            Callable<List<Student>> studentUnit = new Callable<List<Student>>() {
                @Override
                public List<Student> call() throws Exception {
                    return DaoFactory.getInstance().createStudentDao().getAll();
                }
            };
            students = tx.doInTransaction(studentUnit);

            Callable<List<Room>> roomUnit = new Callable<List<Room>>() {
                @Override
                public List<Room> call() throws Exception {
                    return DaoFactory.getInstance().createRoomDao().getAll();
                }
            };
            rooms = tx.doInTransaction(roomUnit);

            req.setAttribute("currentTeacher", currentTeacher);
            req.setAttribute("teachers", teachers);
            req.setAttribute("students", students);
            req.setAttribute("rooms", rooms);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some problems in lessondata controller");
            throw e;
        }
        return PAGE_OK;
    }
}
