import java.io.Serializable;
import java.sql.*;
import java.io.*;
public class Traveler extends Human implements Serializable{

    public int id;
    private int points;

    
    protected Traveler(String name) {
        super(name);
    }

    protected Traveler(String name , int age, int id) {
        super(name, age);
        this.id = id;
    }

    public void view_flight(int flight_num) throws Exception{
        //flight_num == id 
        String sql_get_flight = "select flight from flights where id = ?";

        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test_database", "root", "MySQLmajd1234");

        PreparedStatement pstmt = conn.prepareStatement(sql_get_flight);
        pstmt.setInt(1, flight_num);
        ResultSet obj =  pstmt.executeQuery();
        obj.next();
        byte[] serializedFlight = obj.getBytes("flight");
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedFlight);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Flight flight = (Flight) ois.readObject();

        System.out.println(flight.flight_num);
        System.out.println(flight.name);



    }
    public void book_flight(){
        //if flight_num column in travelers is null then this traveler hasnt booked any flight yet .
    }
    




}
