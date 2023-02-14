package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Class responsible for controlling window when user can set IP and socket number of target device.
 */
public class SettingDialogController {

    /**
     * Field that pass FXML object TextArea which display IP.
     */
    @FXML
    private TextField ipField;
    /**
     * Field that pass FXML object TextArea which display socket number.
     */
    @FXML
    private TextField sockField;

    /**
     * Method is link to particular button and pass IP and socket number to Data class..
     */
    @FXML
    public void setIPFieldAndSockField(){
        try {
            if(Integer.parseInt(sockField.getText()) > 1024){
                Data.getInstance().setIpName(ipField.getText());
                Data.getInstance().setSocketName(Integer.parseInt(sockField.getText()));
                Data.getInstance().setMsg("Ustawiono urządzenie docelowe: " + ipField.getText() +" oraz port: " + Integer.parseInt(sockField.getText()));
            } else{
                Data.getInstance().setMsg("Numer portu musi być większy od 1024");
            }
        } catch (NumberFormatException nfe){
            Data.getInstance().setMsg("Numer portu musi być liczbą");
            System.err.println(nfe);
        }
    }
}
