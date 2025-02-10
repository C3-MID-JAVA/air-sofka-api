package ec.com.airsofka.commands;

import ec.com.airsofka.gateway.data.EmailData;
import ec.com.airsofka.generics.utils.Command;

import java.util.Map;

public class SendEmailCommand extends Command {
    private final String to;
    private final EmailData emailData;

    public SendEmailCommand(String s, EmailData emailData) {
        super(null);
        to = s;
        this.emailData = emailData;
    }

    public String getTo() {
        return to;
    }

    public EmailData getEmailData() {
        return emailData;
    }
}
