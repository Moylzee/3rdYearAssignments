import java.time.LocalDateTime;

public interface NCTBookingSlotWebService {
    public default LocalDateTime getBDateTime(TestCentre t) {
        // returns a date 6 months in the future to add to the booking
        return LocalDateTime.now().plusMonths(6);
    }
}