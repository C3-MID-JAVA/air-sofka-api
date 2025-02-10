package ec.com.airsofka.seat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SeatData {
    public static List<SeatCreatedDTO> getSeatList(String flightId) {
        List<SeatCreatedDTO> seats = new ArrayList<>();
        int rowNumber = 1;
        char[] columns = {'A', 'B', 'C', 'D', 'E', 'F'};

        // FIRST CLASS
        for (int i = 0; i < 2; i++, rowNumber++) {
            for (char col : columns) {
                seats.add(new SeatCreatedDTO(UUID.randomUUID().toString(),rowNumber + String.valueOf(col), rowNumber, String.valueOf(col), SeatClass.FIRST, SeatStatus.AVAILABLE, new BigDecimal("500.00"), flightId));
            }
        }

        // BUSINESS CLASS
        for (int i = 0; i < 3; i++, rowNumber++) {
            for (char col : columns) {
                seats.add(new SeatCreatedDTO(UUID.randomUUID().toString(),rowNumber + String.valueOf(col), rowNumber, String.valueOf(col), SeatClass.BUSINESS, SeatStatus.AVAILABLE, new BigDecimal("300.00"), flightId));
            }
        }

        // ECONOMY CLASS
        for (int i = 0; i < 18; i++, rowNumber++) {
            for (char col : columns) {
                seats.add(new SeatCreatedDTO(UUID.randomUUID().toString(),rowNumber + String.valueOf(col), rowNumber, String.valueOf(col), SeatClass.ECONOMY, SeatStatus.AVAILABLE, new BigDecimal("100.00"), flightId));
            }
        }

        return seats;
    }
}
