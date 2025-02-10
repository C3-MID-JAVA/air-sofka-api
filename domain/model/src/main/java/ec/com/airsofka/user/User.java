package ec.com.airsofka.user;

import ec.com.airsofka.generics.utils.Entity;
import ec.com.airsofka.user.values.UserId;
import ec.com.airsofka.user.values.objects.*;

public class User extends Entity <UserId> {

    private final BirthDate birthDate;
    private final DocumentNumber documentNumber;
    private final DocumentType documentType;
    private final Email email;
    private final FirstLastName firstLastName;
    private final Frequent frequent;
    private final LastLastName lastLastName;
    private final Name name;
    private final NumberOfFlights numberOfFlights;
    private final Password password;
    private final Phone phone;
    private final Prefix prefix;
    private final Role role;
    private final Title title;

    public User(UserId userId, BirthDate birthDate, DocumentNumber documentNumber, DocumentType documentType, Email email, FirstLastName firstLastName, Frequent frequent, LastLastName lastLastName, Name name, NumberOfFlights numberOfFlights, Password password, Phone phone, Prefix prefix, Role role, Title title) {
        super(userId);
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

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public DocumentNumber getDocumentNumber() {
        return documentNumber;
    }

    public Email getEmail() {
        return email;
    }

    public FirstLastName getFirstLastName() {
        return firstLastName;
    }

    public Frequent getFrequent() {
        return frequent;
    }

    public LastLastName getLastLastName() {
        return lastLastName;
    }

    public Name getName() {
        return name;
    }

    public NumberOfFlights getNumberOfFlights() {
        return numberOfFlights;
    }

    public Password getPassword() {
        return password;
    }

    public Phone getPhone() {
        return phone;
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public Role getRole() {
        return role;
    }

    public Title getTitle() {
        return title;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }
}

