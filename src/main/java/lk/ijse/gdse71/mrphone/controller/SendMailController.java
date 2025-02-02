package lk.ijse.gdse71.mrphone.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class SendMailController {

    @FXML
    private TextField txtSubject;

    @FXML
    private TextArea txtbody;

    @Setter
    private String CustomerEmail;

    @FXML
    void sendGmail(ActionEvent event) {
        if (CustomerEmail == null) {
            return;
        }
        final String From = "hirushasilva96@gmail.com";

        String subject = txtSubject.getText();
        String body = txtbody.getText();

        if (subject.isEmpty() || body.isEmpty()) {
            new Alert(Alert.AlertType.WARNING,"Subject and body are required").show();
            return;
        }

        sendEmailWithGmail(From,CustomerEmail,subject,body);
    }
    private void sendEmailWithGmail(String From, String CustomerEmail, String subject, String body) {
        String password = "fjne pbzy ueyv lzuq";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(From, password);
            }
        });
        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(From));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(CustomerEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);

            new Alert(Alert.AlertType.INFORMATION, "Email sent successfully!").show();
        } catch (MessagingException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to send email.").show();
        }
    }
}


