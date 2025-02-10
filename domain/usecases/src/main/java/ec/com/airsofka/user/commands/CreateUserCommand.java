package ec.com.airsofka.user.commands;

import ec.com.airsofka.generics.utils.Command;
import ec.com.airsofka.user.values.objects.DocumentType;
import ec.com.airsofka.user.values.objects.Role;

import java.time.LocalDateTime;

public class CreateUserCommand extends Command {

    private final LocalDateTime birthDate;
    private final String documentNumber;
    private final DocumentType documentType;
    private final String email;
    private final String firstLastName;
    private final Boolean frequent;
    private final String lastLastName;
    private final String name;
    private final Integer numberOfFlights;
    private final String password;
    private final String phone;
    private final String prefix;
    private final Role role;
    private final String title;


    public CreateUserCommand(LocalDateTime birthDate, String documentNumber, DocumentType documentType, String email, String firstLastName, Boolean frequent, String lastLastName, String name, Integer numberOfFlights, String password, String phone, String prefix, Role role, String title) {
        super(null);
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
