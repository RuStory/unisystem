package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 03.06.15
 * Time: 23:01
 * To change this template use File | Settings | File Templates.
 */

/**
 * base controller which indicates command to execute
 */
@WebServlet("/controller")
public class Controller extends HttpServlet{
    private static String CONTROLLER_NAME = "/controller";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("cp1251");
        String page = null;

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(req);

        try{
        page = command.execute(req);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
