package ec.com.airsofka.contact;

import ec.com.airsofka.booking.values.BookingId;
import ec.com.airsofka.contact.values.ContactId;
import ec.com.airsofka.generics.utils.Entity;
import ec.com.airsofka.user.values.objects.Email;
import ec.com.airsofka.user.values.objects.Phone;
import ec.com.airsofka.user.values.objects.Prefix;

public class Contact  extends Entity<ContactId> {

    private final Email email;
    private final Prefix prefix;
    private final Phone phone;
    private  final BookingId bookingId;


    public Contact(ContactId id, Email email, Prefix prefix, Phone phone, BookingId bookingId) {
        super(id);
        this.email = email;
        this.prefix = prefix;
        this.phone = phone;
        this.bookingId = bookingId;
    }

    public Email getEmail() {
        return email;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public Phone getPhone() {
        return phone;
    }

    public BookingId getBookingId() {
        return bookingId;
    }
}
