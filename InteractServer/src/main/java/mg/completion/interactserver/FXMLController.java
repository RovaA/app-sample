package mg.completion.interactserver;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mg.completion.interactserver.socket.IsView;
import mg.completion.interact.socket.event.PushEvent;
import mg.completion.interactserver.socket.SocketHandler;

public class FXMLController implements Initializable, IsView {

    @FXML
    TextField portField;
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
    private void onRun(ActionEvent event) {
        handler.run(Integer.parseInt(portField.getText()));
    }

    @FXML
    private void onUserInput(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) {
            return;
        }
        textArea.appendText(": " + textField.getText() + "\n");
        handler.send(new PushEvent(textField.getText()));
        textField.setText("");
    }

    @Override
    public void receive(String message) {
        textArea.appendText("> " + message + "\n");
    }
}
