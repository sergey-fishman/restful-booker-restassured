package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class Bookingdates {
    private String checkin;
    private String checkout;
}
