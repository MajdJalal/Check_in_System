import java.io.Serializable;
import java.text.DateFormat;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Flight  implements Serializable{

    public int flight_num;
    public String name;
    private LocalDate departure;

    public Flight(String date, int flight_num, String name ) {
        this.name = name;
        this.flight_num = flight_num;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") ;
        //pass the date String 
        departure = LocalDate.parse("26/05/2023", formatter);

    }


    

}
