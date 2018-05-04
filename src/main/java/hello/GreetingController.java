package hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
  
  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }

  @RequestMapping("/getMessage")
  public Message getMessage(@RequestParam(value="id")Integer id) throws SQLException {
    Connection conn = DriverManager.getConnection("jdbc:postgresql://homedb:9831/postgres", "postgres", "GhbdtnVbh");
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("select * from msg.message limit 1");
    Message msg = null;
    while (rs.next()) {
        msg = new Message(rs.getInt("msg_id"), rs.getInt("to_id"), rs.getInt("from_id"),
                          rs.getDate("date_create"), rs.getDate("date_update"));
    }
    rs.close();
    st.close();
    conn.close();

    return msg;
  }



}