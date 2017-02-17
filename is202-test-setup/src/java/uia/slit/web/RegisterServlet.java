package uia.slit.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uia.slit.ejb.RegisterLocal;
import uia.slit.data.Student;

/**
 * This is a servlet, which is also an EJB client.
 * 
 * @author evenal
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    /**
     * This field declaration is also a request for an EJB instance, because
     * it is annotated with EJB. The servlet specifies which EJB it requires
     * by using one of the interfaces (Local, Remote) as the datatype for
     * the field.
     */
    @EJB
    RegisterLocal registerService;

    DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.err.println("RegisterServlet.processRequest():51");
        handleInput(request);
        System.err.println("RegisterServlet.processRequest():53");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.err.println("RegisterServlet.processRequest():56");
            writePage(out);
        }
    }

    protected void handleInput(HttpServletRequest req) {
        System.out.print("\n================\nGot Request: ");
        System.out.println(req.getRequestURL());
        Map<String, String[]> parMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parMap.entrySet()) {
            System.out.print(entry.getKey() + " =");
            for (String v : entry.getValue()) {
                System.out.print(" " + v);
            }
            System.out.println();
        }
        String name = req.getParameter("visitor");
        if (null != name) registerService.register(name);
    }

    protected void writePage(PrintWriter out) {
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        out.println("<!DOCTYPE html>");
        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        out.println("    <head>");
        out.println("        <title>SLIP v0</title>");
        out.println("        <meta HTTP-EQUIV=\"expires\" CONTENT=\"0\"/>");
        out.println("        <meta charset=\"UTF-8\"/>");
        out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>Welcome to SLIP v0</h1>");
        out.println("        <form action=\"register\" method=\"POST\">");
        out.println("          <p>Please enter your name:<br/>");
        out.println("          <input type=\"text\" name=\"visitor\"/></p>");
        out.println("          <input type=\"submit\" value=\"Register\"/>");
        out.println("        </form>");
        out.println("        <h2>Former visitors:</h2>");

        out.println("<table>");
        for (Student v : registerService.getStudents()) {
            out.println("<tr><td>"+v.getName()+"</td><td>"+v.getRegistered()+"</td></tr>");
        }
        out.println("</table>");

        out.println("    </body>");
        out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
