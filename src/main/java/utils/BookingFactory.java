package utils;

import dto.Bookingdates;
import dto.DtoBooking;
import net.datafaker.Faker;

import java.time.LocalDate;

public class BookingFactory {
    static Faker faker = new Faker();

    public static DtoBooking positiveBooking() {
        Bookingdates bookingdates = Bookingdates.builder()
                .checkin(LocalDate.now().toString())
                .checkout(LocalDate.now().plusDays(10).toString())
                .build();
        return DtoBooking.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice((int) faker.number().randomNumber(3))
                .depositpaid(faker.options().option(true, false))
                .bookingdates(bookingdates)
                .additionalneeds(faker.text().text())
                .build();
    }
}
