import java.io.Serializable;
import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;

import java.io.*;

public class Employee extends Human implements Serializable {
    
    public int id;
    private int rank;

    protected Employee(String name, int rank, int id ) {
        super(name);
        this.rank = rank;
        this.id = id;
        
    }
    protected Employee(String name, int age , int rank, int id) {
        super(name, age);
        this.rank = rank;
        this.id = id;
        
    }


    //methods

    public void viewTraveler( int traveler_id) throws Exception {
        //does some functionality 

        //query the traveler from the dataset 
        String sql_get_trav = "SELECT  trav FROM travelers WHERE  id = ? "; 
        String sql1 = "Select flight_num from travelers where id = ?";


        //run this sql command using jdbc 
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test_database", "root", "MySQLmajd1234");

        PreparedStatement pstmt = conn.prepareStatement(sql_get_trav);
        pstmt.setInt(1, traveler_id);
        ResultSet obj =  pstmt.executeQuery();
        //deserialize the travelere object that u got,
        obj.next();
        byte[] serializedData = obj.getBytes("trav");
        
        
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedData);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Traveler trav = (Traveler) ois.readObject();
        //
        PreparedStatement ptsmt1 = conn.prepareStatement(sql1);
        ptsmt1.setInt(1, trav.id);
        ResultSet obj1 =  ptsmt1.executeQuery();
        obj1.next();
        int flight_on = obj1.getInt("flight_num");

        //print all details of the traveler
        System.out.println("traveler name is " + trav.name +" id is " + traveler_id + " who is " + trav.age + " year_old " + "going on the flight_num " + flight_on);
        conn.close();

        
    }
    public void view_flight(int flight_num) throws Exception{
        //flight_num == id 
        String sql_get_flight = "select flight from flights where num = ?";
        String sql1 = "select id from travelers where flight_num = ?";

        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test_database", "root", "MySQLmajd1234");

        PreparedStatement pstmt = conn.prepareStatement(sql_get_flight);
        pstmt.setInt(1, flight_num);
        ResultSet obj =  pstmt.executeQuery();
        obj.next();
        byte[] serializedFlight = obj.getBytes("flight");
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedFlight);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Flight flight = (Flight) ois.readObject();


        System.out.println("Destination : " + flight.name);
        System.out.println("flight number : " + flight.flight_num);

        //show travelers in this flight 
        PreparedStatement pstmt1 = conn.prepareStatement(sql1);
        pstmt1.setInt(1, flight_num);
        ResultSet obj1 = pstmt1.executeQuery();
        while(obj1.next())
        {
            viewTraveler(obj1.getInt("id"));
            
        }
       




    }

    public void add_documents(){

    }
    public void add_bags() {

    }

    







}
