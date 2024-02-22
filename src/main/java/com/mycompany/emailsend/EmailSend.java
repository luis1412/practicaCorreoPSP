/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.emailsend;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.awt.Component;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author luish
 */
public class EmailSend {

    Session mailSession;

    String username;
    String password;
    String puerto;
    String tls;
    String destinatario;
    String mensaje;
    String asunto;
    String host;
    String rutaFichero = null;

    
    public boolean conectar() throws AddressException, MessagingException {

        final Properties prop = new Properties();
        prop.put("mail.smtp.username", username);
        prop.put("mail.smtp.password", password);
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", puerto);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", tls); // TLS
        prop.put("mail.debug", "true");

// Create the Session with the user credentials
        mailSession = Session.getInstance(prop, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(prop.getProperty("mail.smtp.username"),
                        prop.getProperty("mail.smtp.password"));
            }
        });

        Transport a = null;

        a = mailSession.getTransport();
        a.connect();

        return true;
    }

    public void mandarCorreo() throws AddressException, MessagingException, IOException {

        // Prepare the MimeMessage
        Message message = new MimeMessage(mailSession);
// Set From and subject email properties
        message.setFrom(new InternetAddress(username));
        message.setSubject(asunto);

// Set to, cc & bcc recipients        
        InternetAddress[] toEmailAddresses
                = InternetAddress.parse(destinatario);

        message.setRecipients(Message.RecipientType.TO, toEmailAddresses);

//Mail body with plain Text
        /* message.setText("Hello User,"
                + "\n\n If you read this, means mail sent with Java Mail API is successful");*/
//Mail body with HTML
        /* message.setContent("Just discovered that Jakarta Mail is fun and easy to use",
                "text/html");*/

        

      
//Mail with images
//An  HTML code with a link referenced image
        Multipart multipart2 = new MimeMultipart("related");
        MimeBodyPart htmlPart = new MimeBodyPart();
//add reference to your image to the HTML body <img src="cid:some-image-cid" alt="img" />
        String messageBody = "<p>" + mensaje + "</p>";
        htmlPart.setText(messageBody, "utf-8", "html");
// Add the BodyPart to the Multipart object
        multipart2.addBodyPart(htmlPart);
// Add the multipart object to the message
        message.setContent(multipart2);
        if (rutaFichero != null) {
            
    // create an instance of multipart object
        Multipart multipart = new MimeMultipart();

    // create the 1st message body part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
    // Add a plain message (HTML can also be added with setContent)
        messageBodyPart.setText("Mira el fichero adjunto");
    // Add the BodyPart to the Multipart object
        multipart.addBodyPart(messageBodyPart);

    // 2nd. bodyPart with an attached file
        messageBodyPart = new MimeBodyPart();
        String filename = rutaFichero;
        messageBodyPart.attachFile(filename);
    // Add the BodyPart to the Multipart object
        multipart.addBodyPart(messageBodyPart);

    // Add the multipart object to the message
        message.setContent(multipart);
        
                }
// Send the configured message in the session
        Transport.send(message);
    }

    public EmailSend(String host,String username, String password, String puerto, String tls, String destinatario, String mensaje, String asunto) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.puerto = puerto;
        this.tls = tls;
        this.destinatario = destinatario;
        this.mensaje = mensaje;
        this.asunto = asunto;
    }

    
    public Session getMailSession() {
        return mailSession;
    }

    public String getRutaFichero() {
        return rutaFichero;
    }

    public void setRutaFichero(String rutaFichero) {
        this.rutaFichero = rutaFichero;
    }

    
    
    public void setMailSession(Session mailSession) {
        this.mailSession = mailSession;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getTls() {
        return tls;
    }

    public void setTls(String tls) {
        this.tls = tls;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }
    
    
    
    
    
}
