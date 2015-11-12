import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class Listener1
 *
 */
@WebListener
public class Listener1 implements ServletRequestListener {

    public Listener1() {
        // TODO Auto-generated constructor stub
    }

    public void requestDestroyed(ServletRequestEvent event)  { 
    	ServletRequest req = event.getServletRequest();
    	System.out.println("Destroyed request: "+req.getRemoteAddr()+" Type: ");
    	
    }

    public void requestInitialized(ServletRequestEvent event)  { 
    	ServletRequest req = event.getServletRequest();
    	System.out.println("Initialized request: "+req.getRemoteAddr());
    	req.setAttribute("message", "Listener example");
    	ServletContext context = req.getServletContext();
    	Connection con = (Connection)context.getAttribute("mycon");
    	System.out.println("Connection achieved and innitialized");
    	System.out.println("retriving information from the db");
    	try {
			PreparedStatement stmt = con.prepareStatement("Select * from books");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				System.out.println("Bno: "+rs.getInt(1)+" Book Name: "+rs.getString(2)+" Book Price: "+rs.getDouble(3));
			}
			rs.close();
			stmt.close();
			//con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
}
