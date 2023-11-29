import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Booking {
    public String reg;
    public TestCentre t;
    public LocalDateTime dateTime;
    NCTBookingSlotWebService api = new NCTBookingSlotWebService() {};

    public Booking(String reg, TestCentre t, LocalDateTime dateTime) {
        this.reg = reg;
        this.t = t;
        this.dateTime = dateTime;
    }

    // Getter Methods
    public String getReg() {
        return reg;
    }
    public TestCentre getT() {
        return t;
    }
    public LocalDateTime getDateTime() {
        if (dateTime == null) {
            dateTime = api.getBDateTime(t);
        }
        return dateTime;
    }
    // Method to Check if the Date Entered is Invalid
    public boolean checkInvalidDate() throws Exception {
        LocalDateTime currentDate = LocalDateTime.now();
        if (dateTime.isBefore(currentDate)) {
            throw new Exception("Invalid Date");
        }
        return true;
    }

    // This function will overwrite the reg with and updated reg 
    public void setNewReg(String newReg) {
        this.reg = newReg;
    }
    // Method to create A 6-Digit Booking Number for each Booking
    public String createBookingNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public String toString() {
        // format the date and time 
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String date = dateTime.format(dateFormatter);
        String time = dateTime.format(timeFormatter);

        return (
            "Booking id: " +createBookingNumber()+
            "\nVehicle Registration Number: " +getReg()+
            "\nCentre: " +t.getName()+
            "\nAddress: " +t.getAddress()+
            "\nDate: " +date+
            "\nTime: " +time
        );
    }
}