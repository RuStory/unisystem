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
public class TeacherCommand implements ActionCommand {
    private final String PAGE_OK = "/view/redirect.jsp";

    @Override
    public String execute(HttpServletRequest req) throws Exception{

        TransactionManager tx = new JDBCTransactionManager();
        final Teacher newTeacher = new Teacher(req.getParameter("name"), req.getParameter("surname"));

        final Integer currentTeacher = req.getParameter("currentTeacher") == null ? 1 : Integer.parseInt(req.getParameter("currentTeacher"));

        try {
            Callable<Boolean> teacherUnit = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return DaoFactory.getInstance().createTeacherDao().createNewTeacher(newTeacher);
                }
            };
            tx.doInTransaction(teacherUnit);

            req.setAttribute("currentTeacher", currentTeacher);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some problems in teacher controller");
            throw e;
        }
        return PAGE_OK;
    }
}
