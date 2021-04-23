package gui;
import javafx.event.ActionEvent;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MainController {

    @FXML
    private TextField emailToField;

    @FXML
    private TextField emailFromField;

    @FXML
    private PasswordField emailPasswordField;

    @FXML
    private TextField emailSubjectField;

    @FXML
    private TextArea emailMessageField;

    @FXML
    private Button sendEmailButton;

    @FXML
    private Label sentBoolValue;
    @FXML
    private ImageView FOnd;

    @FXML
    void buttonClicked(ActionEvent event) {
sendEmail();
    }

    public void sendEmail(){
        String to = emailToField.getText();
        String from = emailFromField.getText();
        String host = "smtp.gmail.com";
        final String username = emailFromField.getText();
        final String password = emailPasswordField.getText();

        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(emailSubjectField.getText());
            m.setText(emailMessageField.getText());

            //send mail

            Transport.send(m);
            sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

    }
}