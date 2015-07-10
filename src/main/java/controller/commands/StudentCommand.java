package controller.commands;

import controller.ActionCommand;
import dao.DaoFactory;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Student;
import entities.Teacher;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

/**
 * controller that provides authentication
 * by checking login and password
 */
public class StudentCommand implements ActionCommand {
    private final String PAGE_OK = "/view/redirect.jsp";

    @Override
    public String execute(HttpServletRequest req) throws Exception{

        TransactionManager tx = new JDBCTransactionManager();
        final Student newStudent = new Student(req.getParameter("name"), req.getParameter("surname"));

        final Integer currentTeacher = req.getParameter("currentTeacher") == null ? 1 : Integer.parseInt(req.getParameter("currentTeacher"));

        try {
            Callable<Boolean> studentUnit = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return DaoFactory.getInstance().createStudentDao().createNewStudent(newStudent);
                }
            };
            tx.doInTransaction(studentUnit);

            req.setAttribute("currentTeacher", currentTeacher);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some problems in student controller");
            throw e;
        }
        return PAGE_OK;
    }
}
