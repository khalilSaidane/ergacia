package GUI;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import entity.User;

public class Mail {
	private static int code ;
	public static int  sendMail(User u ){
		 code = (int) (Math.random() * 1000 + 1);
		   try{
	 String host ="smtp.gmail.com" ;
     String user = "ergacia2019@gmail.com";
     String pass = "ergacia__2019";
     String to = u.getEmail();
     String from = "ergacia";
     String subject = "This is confirmation number for your registration . Please insert this number to activate your account.";
     String messageText = "your confirmation code is :"+code;
     boolean sessionDebug = false;

     Properties props = System.getProperties();
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.host", host);
     props.put("mail.smtp.port", "587");
     props.put("mail.smtp.auth", "true");
     props.put("mail.smtp.starttls.required", "true");
     java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
     Session mailSession = Session.getDefaultInstance(props, null);
     mailSession.setDebug(sessionDebug);
     Message msg = new MimeMessage(mailSession);
     msg.setFrom(new InternetAddress(from));
     InternetAddress[] address = {new InternetAddress(to)};
     msg.setRecipients(Message.RecipientType.TO, address);
     msg.setSubject(subject); msg.setSentDate(new Date());
     msg.setText(messageText);

    Transport transport=mailSession.getTransport("smtp");
    transport.connect(host, user, pass);
    transport.sendMessage(msg, msg.getAllRecipients());
    transport.close();
    System.out.println("message send successfully");
		   }catch(Exception ex)
	        {
	            System.out.println(ex);
	            return code;
	        }
     return code;
		
	}


}
