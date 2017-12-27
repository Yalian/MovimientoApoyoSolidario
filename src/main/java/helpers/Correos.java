package helpers;



import javafx.collections.ObservableList;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public  class Correos {
    String miCorreo;
    String miContraseña;
    String servidorSMTP = "smtp.gmail.com";
    String puertoEnvio = "587";
    String asunto;
    String cuerpo;


    public Correos(String miCorreo, String miContraseña) {
        this.miCorreo = miCorreo;
        this.miContraseña = miContraseña;
    }

    public void EnviarMultiples(ObservableList<CorreosHelper> destinatarios, String asunto, String mensaje) throws MessagingException {

        for (CorreosHelper s:destinatarios){
            EnviarCorreo(s.getCorreo(),asunto,mensaje);

            System.out.println("Enviando Mensajes..");
        }

        System.out.println("Mensajes enviados");

    }


    public void EnviarCorreo(String mailReceptor, String asunto, String cuerpo) throws MessagingException {
        this.asunto = asunto;
        this.cuerpo = cuerpo;

        Properties props = new Properties();
        props.put("mail.smtp.user", miCorreo);
        props.put("mail.smtp.host", servidorSMTP);
        props.put("mail.smtp.port", puertoEnvio);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", puertoEnvio);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");

        SecurityManager security = System.getSecurityManager();


        Authenticator auth = new Correos.autentificadorSMTP();
        Session session = Session.getInstance(props, auth);
        session.setDebug(true);

        MimeMessage msg = new MimeMessage(session);
        msg.setText(cuerpo);
        msg.setSubject(asunto);
        msg.setFrom(new InternetAddress(miCorreo));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailReceptor));
        Transport.send(msg);


    }

    private class autentificadorSMTP extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(miCorreo, miContraseña);
        }
    }

}
