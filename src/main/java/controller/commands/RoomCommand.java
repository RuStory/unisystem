package controller.commands;

import controller.ActionCommand;
import dao.DaoFactory;
import dao.jdbc.transaction.JDBCTransactionManager;
import dao.jdbc.transaction.TransactionManager;
import entities.Room;
import entities.Teacher;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;

/**
 * controller that provides authentication
 * by checking login and password
 */
public class RoomCommand implements ActionCommand {
    private final String PAGE_OK = "/view/redirect.jsp";

    @Override
    public String execute(HttpServletRequest req) throws Exception{

        TransactionManager tx = new JDBCTransactionManager();
        final Room newRoom = new Room(Integer.parseInt(req.getParameter("number")));

        final Integer currentTeacher = req.getParameter("currentTeacher") == null ? 1 : Integer.parseInt(req.getParameter("currentTeacher"));

        try {
            Callable<Boolean> roomUnit = new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return DaoFactory.getInstance().createRoomDao().createNewRoom(newRoom);
                }
            };
            tx.doInTransaction(roomUnit);

            req.setAttribute("currentTeacher", currentTeacher);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some problems in room controller");
            throw e;
        }
        return PAGE_OK;
    }
}
