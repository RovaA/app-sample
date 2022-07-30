package mg.completion.interact;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mg.completion.interact.socket.IsView;
import mg.completion.interact.socket.event.SendEvent;
import mg.completion.interact.socket.SocketHandler;

public class FXMLController implements Initializable, IsView {

    @FXML
    TextField hostField;
    @FXML
    TextField portField;
    @FXML
    Button connectButton;
    @FXML
    TextArea textArea;
    @FXML
    TextField textField;

    protected SocketHandler handler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        handler = new SocketHandler(this);
    }

    @FXML
    private void onConnect(ActionEvent event) {
        handler.connect(hostField.getText(), Integer.parseInt(portField.getText()));
        connectButton.setDisable(true);
    }

    @FXML
    private void onUserInput(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) {
            return;
        }
        textArea.appendText(": " + textField.getText() + "\n");
        handler.send(new SendEvent(textField.getText()));
        textField.setText("");
    }

    @Override
    public void receive(String message) {
        textArea.appendText("> " + message + "\n");
    }
}
