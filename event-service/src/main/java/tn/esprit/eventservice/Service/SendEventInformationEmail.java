package tn.esprit.eventservice.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import tn.esprit.eventservice.Entity.Event;
import org.springframework.stereotype.Service;

@Service
public class SendEventInformationEmail {

    public void sendEventInformationEmail(String userEmail, Event event) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");  // Use port 465 for secure connection
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");  // Enable SSL
        properties.put("mail.smtp.starttls.enable", "true");  // Enable STARTTLS

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("oumaima.ayachi@esprit.tn", "54029564");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("oumaima.ayachi@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Event Information");
            message.setText("You have joined the event: " + event.getName() + "\nEvent Date: " + event.getDate() + "\nLocation: " + event.getLocation());

            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
