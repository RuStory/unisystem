package controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 03.06.15
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */

/**
 * class for creating command
 */
public class ActionFactory {
    /**
     * defines command and creates it
     * @param request
     * @return
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = null;
        String action = request.getParameter("command");
        if(action == null) {
            action = "schedule";
        }

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return current;
    }
}
