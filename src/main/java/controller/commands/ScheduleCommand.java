package controller.commands;

import controller.ActionCommand;
import dao.DaoFactory;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Lesson;
import entities.Teacher;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * controller that provides authentication
 * by checking login and password
 */
public class ScheduleCommand implements ActionCommand {
    private final String PAGE_OK = "/view/schedule.jsp";

    @Override
    public String execute(HttpServletRequest req) throws Exception{

        TransactionManager tx = new JDBCTransactionManager();
        List<Teacher> teachers = new ArrayList<>();
        List<Lesson> lessons = new ArrayList<>();

        final Integer currentTeacher = req.getParameter("currentTeacher") == null ? 1 : Integer.parseInt(req.getParameter("currentTeacher"));

        try {
            Callable<List<Teacher>> teacherUnit = new Callable<List<Teacher>>() {
                @Override
                public List<Teacher> call() throws Exception {
                    return DaoFactory.getInstance().createTeacherDao().getAll();
                }
            };
            teachers = tx.doInTransaction(teacherUnit);

            Callable<List<Lesson>> lessonsUnit = new Callable<List<Lesson>>() {
                @Override
                public List<Lesson> call() throws Exception {
                    return DaoFactory.getInstance().createLessonDao().getLessonsByTeacher(currentTeacher);
                }
            };
            lessons = tx.doInTransaction(lessonsUnit);


            req.setAttribute("teachers", teachers);
            req.setAttribute("currentTeacher", currentTeacher);
            req.setAttribute("lessons", lessons);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some problems in schedule controller");
            throw e;
        }
        return PAGE_OK;
    }
}
