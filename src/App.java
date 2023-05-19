
import java.sql.*;
import java.io.*;
import java.util.*;

public class App {
    /*static byte[] flight1_data;
    static byte[] flight2_data;
    static byte[] flight3_data;
    static byte[] trav1_data;
    static byte[] trav2_data;
    static byte[] trav3_data;
    static byte[] trav4_data;
    static byte[] emp1_data;
    static byte[] emp2_data;*/
    public static void main(String[] args) throws Exception {
        /*System.out.println("Hello, World!");

        //sample test on the dataSet 
        Employee emp1 = new Employee("majd", 20, 6, 114);
        Employee emp2 = new Employee("baraa", 23, 8, 119);

        Traveler trav1 = new Traveler("fadi", 21, 2245);
        Traveler trav2 = new Traveler("ahmed", 29, 2641);
        Traveler trav3 = new Traveler("adel", 21, 1126);
        Traveler trav4 = new Traveler("manar", 21, 9912);


        Flight flight1 = new Flight("26/5/2023", 167, "IST");
        Flight flight2 = new Flight("26/6/2023", 112, "DXB");
        Flight flight3 = new Flight("3/6/2023", 113, "AUH");
        
*/
        //seialzie 
       /* */ /* try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(baos);
            out.writeObject(flight1);
            flight1_data = baos.toByteArray();
            baos.reset();
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            //
            out.writeObject(flight2);
            flight2_data = baos.toByteArray();
            baos.reset();
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            //
            out.writeObject(flight3);
            flight3_data = baos.toByteArray();
            baos.reset();
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            ///
            out.writeObject(trav1);
            trav1_data = baos.toByteArray();
            baos.reset();
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            //
            out.writeObject(trav2);
            trav2_data = baos.toByteArray();
            baos.reset();
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            //
            out.writeObject(trav3);
            trav3_data = baos.toByteArray();
            baos.reset();
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            //
            out.writeObject(trav4);
            trav4_data = baos.toByteArray();
            baos.reset();
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            //
            
            out.writeObject(emp1);
            emp1_data = baos.toByteArray();
            baos.reset();
            baos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(baos);
            //
            out.writeObject(emp2);
            emp2_data = baos.toByteArray();
            baos.reset();
            

            
            out.close();
            
        } catch(IOException i) {
            i.printStackTrace();
        }
     */
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");
         }
         catch(ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
         }*/

         Scanner sc = new Scanner(System.in);
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test_database", "root", "MySQLmajd1234");
        //entering the employer account :
        System.out.println("Enter ur employee id : ");
        String sql0 = " select emp from employees where id = ?";
        PreparedStatement ptsmt0 = conn.prepareStatement(sql0);
        ptsmt0.setInt(1, sc.nextInt());
        ResultSet obj0 =  ptsmt0.executeQuery();
        obj0.next();
        
        byte[] serializedObject =  obj0.getBytes("emp");
        
        ByteArrayInputStream bais = new ByteArrayInputStream(serializedObject);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Employee emp_obj = (Employee) ois.readObject();
        //having 2 options looking for a flight or a traveler
        System.out.println("what do u wanna do : ");
        System.out.println("1)search flight:");
        System.out.println("2)search traveler:");
        int option_num  = sc.nextInt();
        if(option_num == 1){
            System.out.println("Enter a flight number :");
            int flight_num = sc.nextInt();
            emp_obj.view_flight(flight_num);
        } else if(option_num == 2){
            System.out.println("Enter a traveler  id :");
            int traveler_id = sc.nextInt();
            emp_obj.viewTraveler(traveler_id);
        }

         /*Statement statement = conn.createStatement();
         String sql1  = "INSERT INTO employees (id, emp) VALUES (?, ?) ";
         String sql2 = "insert into flights (num, flight) values (?, ?)";
         String sql3 = "insert into travelers (id, trav, flight_num, employee) values (?, ? , ?, ?)";
         //employees;
         PreparedStatement pstmt = conn.prepareStatement(sql1);
         pstmt.setInt(1, emp1.id);
         pstmt.setBytes(2, emp1_data);
         pstmt.execute();
         //
         PreparedStatement pstmt2 = conn.prepareStatement(sql1);
         pstmt2.setInt(1, emp2.id);
         pstmt2.setBytes(2, emp2_data);
         pstmt2.execute();
         //flights inserting 
         PreparedStatement pstmt5 = conn.prepareStatement(sql2);
         pstmt5.setInt(1, flight1.flight_num);
         pstmt5.setBytes(2, flight1_data);
         pstmt5.execute();
         //
         PreparedStatement pstmt3 = conn.prepareStatement(sql2);
         pstmt3.setInt(1, flight2.flight_num);
         pstmt3.setBytes(2, flight2_data);
         pstmt3.execute();
         //
         PreparedStatement pstmt4 = conn.prepareStatement(sql2);
         pstmt4.setInt(1, flight3.flight_num);
         pstmt4.setBytes(2, flight3_data);
         pstmt4.execute();
         //travelers 
         PreparedStatement pstmt6 = conn.prepareStatement(sql3);
         pstmt6.setInt(1, trav1.id);
         pstmt6.setBytes(2, trav1_data);
         pstmt6.setInt(3, 167);
         pstmt6.setInt(4, 114);
         pstmt6.execute();
         //
         PreparedStatement pstmt7 = conn.prepareStatement(sql3);
         pstmt7.setInt(1, trav2.id);
         pstmt7.setBytes(2, trav2_data);
         pstmt7.setInt(3, 167);
         pstmt7.setInt(4, 114);
         pstmt7.execute();
         //
         PreparedStatement pstmt8 = conn.prepareStatement(sql3);
         pstmt8.setInt(1, trav3.id);
         pstmt8.setBytes(2, trav3_data);
         pstmt8.setInt(3, 112);
         pstmt8.setInt(4, 119);
         pstmt8.execute();
         //
         PreparedStatement pstmt9 = conn.prepareStatement(sql3);
         pstmt9.setInt(1, trav4.id);
         pstmt9.setBytes(2, trav4_data);
         pstmt9.setInt(3, 113);
         pstmt9.setInt(4, 119);
         pstmt9.execute();*/

         
         
    }
}


//To serialize an object means to convert its state to a byte stream so that the byte stream can be reverted back into a copy of the object. A Java object is serializable if its class or any of its superclasses implements either the java.io.Serializable interface or its subinterface, java.io.Externalizable. Deserialization is the process of converting the serialized form of an object back into a copy of the object.

//When storing a serialized Java object in a database, you would typically use a binary data type such as BLOB (Binary Large OBject) or VARBINARY. This is because the serialized object is a sequence of bytes that does not have a fixed length, and cannot be represented using a standard SQL data type such as INTEGER.

//To store the serialized object in a database, you would first serialize the object using the ObjectOutputStream class in Java. This would give you a byte array representing the serialized object. You could then insert this byte array into a BLOB or VARBINARY column in your database using an appropriate SQL statement.


//the method is ...
//ObjectOutputStream oos = new ObjectOutputStream(bos);
//oos.writeObject(obj);
//byte[] data = bos.toByteArray();
