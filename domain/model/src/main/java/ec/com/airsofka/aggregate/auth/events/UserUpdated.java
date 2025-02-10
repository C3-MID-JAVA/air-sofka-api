package ec.com.airsofka.aggregate.auth.events;

import ec.com.airsofka.generics.domain.DomainEvent;
import ec.com.airsofka.user.values.objects.DocumentType;
import ec.com.airsofka.user.values.objects.Role;

import java.time.Instant;
import java.time.LocalDateTime;

public class UserUpdated extends DomainEvent {

    private String userId;
    private LocalDateTime birthDate;
    private String documentNumber;
    private DocumentType documentType;
    private String email;
    private String firstLastName;
    private Boolean frequent;
    private String lastLastName;
    private String name;
    private Integer numberOfFlights;
    private String password;
    private String phone;
    private String prefix;
    private Role role;
    private String title;


    public UserUpdated(String userId, LocalDateTime birthDate, String documentNumber, DocumentType documentType, String email, String firstLastName, Boolean frequent, String lastLastName, String name, Integer numberOfFlights, String password, String phone, String prefix, Role role, String title) {
        super(EventsAuthEnum.USER_UPDATED.name());
        this.userId = userId;
        this.birthDate = birthDate;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.email = email;
        this.firstLastName = firstLastName;
        this.frequent = frequent;
        this.lastLastName = lastLastName;
        this.name = name;
        this.numberOfFlights = numberOfFlights;
        this.password = password;
        this.phone = phone;
        this.prefix = prefix;
        this.role = role;
        this.title = title;
    }

    public UserUpdated() {
        super(EventsAuthEnum.USER_UPDATED.name());
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public Boolean getFrequent() {
        return frequent;
    }

    public String getLastLastName() {
        return lastLastName;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfFlights() {
        return numberOfFlights;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getPrefix() {
        return prefix;
    }

    public Role getRole() {
        return role;
    }

    public String getTitle() {
        return title;
    }

}
