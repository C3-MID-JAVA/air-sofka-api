package ec.com.airsofka.gateway.dto;

public class ContactDTO {
    private  String id;
    private  String email;
    private  String prefix;
    private  String phone;
    private  String bookingId;

    public ContactDTO(String id, String email, String prefix, String phone, String bookingId) {
        this.id = id;
        this.email = email;
        this.prefix = prefix;
        this.phone = phone;
        this.bookingId = bookingId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
