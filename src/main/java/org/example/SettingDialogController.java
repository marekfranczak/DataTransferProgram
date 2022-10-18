package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SettingDialogController {

    @FXML
    private TextField ipField;
    @FXML
    private TextField sockField;

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
