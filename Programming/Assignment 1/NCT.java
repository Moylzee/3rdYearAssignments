import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.junit.Test;

public class NCT {

    TestCentre t = new TestCentre("Centre-1", "Galway");
    LocalDateTime d = LocalDateTime.of(2023, 9, 15, 12, 46); 
    Booking b = new Booking("131-MO-1234", t, d); 

    @Test
    public void testBooking() {
        // Test that the reg is not empty or null and matches the format of a Car Registration 
        assertTrue(b.getReg().matches("\\d{3}-[A-Z]{2}-\\d{4}"));
        // Compare the Test Centre and Date Time
        assertEquals(t, b.getT());
        assertEquals(d, b.getDateTime());
        // query for the test centre of the booking
        assertEquals(t, b.getT());
    }

    // Test the the vehicle registration is updated
    @Test
    public void testUpdate() {
        // update the reg
        b.setNewReg("161-D-246");
        // Test that it updated 
        assertEquals("161-D-246", b.getReg());
    }

    // Test that the Centre has a name and address 
    @Test
    public void testCentreCreation() {
        assertNotNull(t.getName()); 
        assertNotEquals("", t.getName());
        assertNotNull(t.getAddress()); 
        assertNotEquals("", t.getAddress());
    }

    // Test for Booking with No date or time and Gives a date itself 
    @Test
    public void bookingNoDateTime() {
        TestCentre t2 = new TestCentre("Centre-1", "Galway");
        Booking b2 = new Booking("12-MO-1234", t2, null); 
        LocalDateTime d2 = b2.getDateTime();
        assertNotNull(d2);
    }

    // Checks if date is passed 
    @Test
    public void invalidDate() {
        TestCentre t3 = new TestCentre("Centre-1", "Galway");
        LocalDateTime d3 = LocalDateTime.of(2023, 9, 15, 12, 46);
        Booking b3 = new Booking("12-MO-1234", t3, d3);

        try {
            b3.checkInvalidDate();
            fail("Invalid Date");
        } catch (Exception e) {
            System.out.println("Exception message: " + e.getMessage());
        }
    }

    // Check that a Booking Number Exists and that it is 6 digits long
    @Test
    public void checkBookingNumber() {
        TestCentre t4 = new TestCentre("Centre-1", "Galway");
        LocalDateTime d4 = LocalDateTime.of(2024, 9, 15, 12, 46); 
        Booking b4 = new Booking("12-MO-1234", t4, d4); 
        // Check that Booking Number matches the format of a six digit Number  
        String bookingNum = b4.createBookingNumber();
        assertTrue(bookingNum.matches("\\d{6}"));
        
    }
    public static void main(String[] args) {
        TestCentre t4 = new TestCentre("Centre-1", "Galway");
        LocalDateTime d4 = LocalDateTime.of(2024, 9, 15, 12, 46); 
        Booking b4 = new Booking("131-MO-1234", t4, d4); 
        System.out.println(b4.toString());
    }
}
