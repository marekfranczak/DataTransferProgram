package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Class responsible for controlling application graph layout
 * @author Marek Frańczak
 * @since 1.0.0
 */
public class Controller {

    /**
     * Field that pass FXML object BorderPane where application state are display and shares tools to control application.
     */
    @FXML
    private BorderPane mainWindow;

    /**
     * Field that pass FXML object TextArea which display application status.
     */
    @FXML
    private TextArea msgArea;

    /**
     * Instance of class that support sending files.
     */
    private Server server;
    /**
     * Instance of class that support receiving files.
     */
    private Client client;

    /**
     * Method is link to particular button and enables send file.
     */
    @FXML
    public void sendFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik do wysłania");
        File selectedFile =  fileChooser.showOpenDialog(mainWindow.getScene().getWindow());
        if(selectedFile != null) {
            if (Data.getInstance().getIpName() != null && Data.getInstance().getSocketName() != 0) {
                Data.getInstance().setDirection(selectedFile.getAbsolutePath());
                server = new Server(Data.getInstance().getSocketName(), selectedFile.getAbsolutePath());
                server.run();
            } else {
                Data.getInstance().setMsg("Przed wyłaniem pliku należy wskazać maszynę docelową oraz port.");
            }
        } else {
            Data.getInstance().setMsg("Nie wybrano pliku.");
        }
        msgArea.setText(Data.getInstance().toString());
    }

    /**
     * Method is link to particular button and enables save file.
     */
    @FXML
    public void saveFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz miejsce do zapisu pliku");
        File selectedFile = fileChooser.showSaveDialog(mainWindow.getScene().getWindow());
        if(selectedFile != null) {
            if (Data.getInstance().getIpName() != null && Data.getInstance().getSocketName() != 0) {
                Data.getInstance().setDirection(selectedFile.getAbsolutePath());
                client = new Client(Data.getInstance().getIpName(), Data.getInstance().getSocketName(), selectedFile.getAbsolutePath());
                client.go();
            } else{
                Data.getInstance().setMsg("Przed odebraniem pliku należy wskazać nadawcę oraz port.");
            }
        } else {
            Data.getInstance().setMsg("Nie wybrano miejsca zapisu.");
        }
        msgArea.setText(Data.getInstance().toString());
    }

    /**
     * Method that close application.
     */
    @FXML
    public void exit(){
        System.exit(0);
    }

    /**
     * Method is link to particular button and enables display instruction 'How to send and receive file'.
     */
    @FXML
    public void helpDialog(){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("Pomoc");
        dialog.setHeaderText("Instrukcja obsługi programu");
        dialog.setContentText(Data.getInstance().getHelp());
        dialog.show();
    }

    /**
     * Method is link to particular button and enables display information about program.
     */
    @FXML
    public void aboutDialog(){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("O programie");
        dialog.setHeaderText(null);
        String s = "\t\t\t\t\t****\n\t\tProgram stworzony na potrzeby przedmiotu\n\t\t\tProgramowanie Obiektowe\n\t\t\t\tautor: Marek Frańczak\n\t\t\t\t\t****";
        dialog.setContentText(s);
        dialog.show();
    }

    /**
     * Method is link to particular button and enables set IP and socket number for target device.
     */
    @FXML
    public void setIpAndSockMenu(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("Ustawienia urządzenia docelowego");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("settingDialog.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e){
            e.printStackTrace();
            Data.getInstance().setMsg("Nie można otworzyć okna.");
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get()== ButtonType.OK){
            SettingDialogController settingDialogController = fxmlLoader.getController();
            settingDialogController.setIPFieldAndSockField();
        }
        msgArea.setText(Data.getInstance().toString());
    }
}

