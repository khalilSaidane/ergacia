package GUI;


import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import static services.ServiceUser.ez;
import entity.User;


import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceUserRemote;

/**
 * FXML Controller class
 *
 * @author HP I3
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField EmailTf;
    @FXML
    private PasswordField PwdTf;

    public static User CurrentUser;
    @FXML
    private Button signUp;
    @FXML
    private Button LoginBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    
    }    

    public static User u =new User(); 

   public static User userStatic = new User();
   
    

    @FXML
    private void Login(ActionEvent event) throws NamingException, IOException {
    	Hashtable ht=new Hashtable();
		ht.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
     	ht.put("java.naming.provider.url","http-remoting://localhost:18080");
		ht.put("jboss.naming.client.ejb.context",true);
		Context ctx = new InitialContext(ht);
		Object obj = ctx.lookup("/ergaciaBU-ear/ergaciaBU-ejb/ServiceUser!services.ServiceUserRemote");
		
		ServiceUserRemote service = (ServiceUserRemote) obj;
		Boolean verif=service.LoginVerification(EmailTf.getText(), PwdTf.getText());
		u = service.ReturnUserbymailpwd(EmailTf.getText(), PwdTf.getText());  //do u mean connected user if  yes  pls change it to CurrentUser !
		
        
        	   

		System.out.println(verif);
		userStatic = service.ReturnUserbymailpwd(EmailTf.getText(), PwdTf.getText());
		if(verif)

		{

			            //Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        //Parent root = FXMLLoader.load(getClass().getResource("Postulation.fxml"));
		          /*  CurrentUser = service.ReturnUserbymailpwd(EmailTf.getText(), PwdTf.getText());
		            if(CurrentUser.getRole().equals("Candidat"))
		             {
			             Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                         Parent root = FXMLLoader.load(getClass().getResource("HomeCondidateFXML.fxml"));
                         Scene scene = new  Scene (root);
                         primaryStage.setScene(scene);
                         primaryStage.setTitle("Ergacia");
                         primaryStage.show();
		           }*/
                    
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
                        Scene scene = new  Scene (root);
                        primaryStage.setScene(scene);
                        primaryStage.setTitle("Ergacia");
                        primaryStage.show();
                        

		}
	
		
    }

    @FXML
    private void SignUp(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/SignUpFXML.fxml"));
            signUp.getScene().setRoot(root);
        } catch (IOException ex) {
            
        }
    }
  
}
