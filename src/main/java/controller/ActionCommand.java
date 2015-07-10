package controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 03.06.15
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public interface ActionCommand {
    String execute(HttpServletRequest request) throws Exception;
}
