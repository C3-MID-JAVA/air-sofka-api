package ec.com.airsofka.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contact")
public class ContactEntity {

    @Id
    private  String id;
    private  String email;
    private  String prefix;
    private  String phone;
    private  String idBooking;

    public ContactEntity() {
    }

    public ContactEntity(String id, String email, String prefix, String phone, String idBooking) {
        this.id = id;
        this.email = email;
        this.prefix = prefix;
        this.phone = phone;
        this.idBooking = idBooking;
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

    public String getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }
}
