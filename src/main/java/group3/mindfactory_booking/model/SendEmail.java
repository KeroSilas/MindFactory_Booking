package group3.mindfactory_booking.model;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

// Code is from https://netcorecloud.com/tutorials/send-email-in-java-using-gmail-smtp/#:~:text=How%20To%20Send%20Email%20In%20Java%20Using%20Gmail,Step%204%20-%20Send%20Email%20with%20Attachment.%20
// Password authentication approach from https://www.youtube.com/watch?v=ugIUObNHZdo (3:00~)

// For the error: https://stackoverflow.com/questions/55276768/how-to-prevent-java-mail-expected-resource-not-found-warnings-from-camel-smtp
public class SendEmail {

    public void sendEmail(String email, int bookingID) {

        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "noreplyMindfactory@gmail.com";

        // Assuming you are sending email from through gmail's smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object and pass
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("noreplyMindfactory@gmail.com", "uclynhanraxuzump");

            }

        });
        //session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Her er din bookingkode til Mindfactory by ECCO");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                File f = new File("src/main/resources/group3/mindfactory_booking/images/MF_POSITVE_COLOR.jpg");

                attachmentPart.attachFile(f);
                textPart.setText(String.valueOf(bookingID));
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            message.setContent(multipart);

            System.out.println("Sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}