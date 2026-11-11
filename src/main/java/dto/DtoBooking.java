package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class DtoBooking {
    private String firstname;
    private String lastname;
    private Integer totalprice;
    private boolean depositpaid;
    private Bookingdates bookingdates;
    private String additionalneeds;
}
