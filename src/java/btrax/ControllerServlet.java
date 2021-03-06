/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package btrax;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AtweLu01
 */
@WebServlet(name = "ControllerServlet", loadOnStartup = 1, urlPatterns = {"/login","/logout"})
public class ControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
        
            String userPath = request.getServletPath();
            String url = null;

            if (userPath.equals("/logout")) {
                HttpSession session = request.getSession(false);
            
                if (session != null) {
                    session.invalidate();
                }
                    url = "/WEB-INF/jsp/index.jsp";            
            }
            else {
                url = "/WEB-INF/view" + userPath + ".jsp";
            }
        

            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
        
        String userPath = request.getServletPath();
        String url = null;
        
        if (userPath.equals("/login")) {
            
             UserBean user = new UserBean();
             user.setUserName(request.getParameter("un"));
             user.setPassword(request.getParameter("pwd"));
             
             user = UserDAO.login(user);
             
             if (user.isValid()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser",user);
              url = "/WEB-INF/view/home.jsp";
             }
             else
             {
                 url = "/WEB-INF/jsp/index.jsp";
             }
        }
        else {
            url = "/WEB-INF/view" + userPath + ".jsp";
        }
        

            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
