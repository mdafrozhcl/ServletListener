

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.websocket.Session;

/**
 * Application Lifecycle Listener implementation class Listener2
 *
 */
@WebListener
public class Listener2 implements ServletContextListener, HttpSessionListener {

    /**
     * Default constructor. 
     */
    public Listener2() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent sessionEvent)  { 
    	Session s = (Session) sessionEvent.getSession();
    	System.out.println("Session created: "+sessionEvent.getSession().getId());
    	System.out.println("Query String: "+s.getQueryString());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent sessionEvent)  { 
    	System.out.println("Session distroyed: "+sessionEvent.getSession().getId());
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent contextEvent)  { 
    	System.out.println("Context Destroyed: "+contextEvent.getServletContext().getServletContextName());
    	ServletContext context = contextEvent.getServletContext();
    	Connection con = (Connection)context.getAttribute("mycon");
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Connection closed::");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent contextEvent)  { 
         try{
        	 Class.forName("oracle.jdbc.driver.OracleDriver");
        	 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
        	 ServletContext context = contextEvent.getServletContext();
        	 context.setAttribute("mycon", con);
        	 System.out.println("Attribute added [mycon]");
         }catch(Exception e){}
    	
    }
	
}
