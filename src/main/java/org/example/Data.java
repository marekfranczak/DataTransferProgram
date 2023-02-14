package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that store all data necessary to application work correctly.
 * @author Marek Frańczak
 * @since 1.0.0
 */
public class Data {

    /**
     * Instance of class Data. Class is created once.
     */
    private static Data data;
    /**
     * List that storage all communications from application.
     */
    private List<String> msg = new ArrayList<>();
    /**
     * Field that storage ip number.
     */
    private String ipName;
    /**
     * Field that storage file path.
     */
    private String direction;
    /**
     * Field that storage socket number.
     */
    private int socketName = 0;
    /**
     * Field that storage instruction 'How to send and receive file'.
     */
    private String help = "Aby skutecznie skutecznie wysłać lub pobrać plik proszę postępować według poniższych punktów." +
            "\n\t1. Proszę wybrać z menu zakładkę ustawienie->Ustaw maszynę docelową." +
            "\n\t2. Uzupełnić pola w oknie którre się pojawiło. UWAGA! Aby skutecznie uzupełnić pola należy użyć portu większego niż 1024." +
            "Urządzenie może być reprezentowane przez adres IP lub przez nazwę własną." +
            "\n\t3. Gdy wyświetli się komunikat \"Ustawiono urządzenie docelowe: 'urządzenie' oraz port: '0123'\" można przystąpić do wysyłania lub " +
            "odbierania plliku." +
            "\n\t4. Należy kliknąć przycisk \"Wyślij plik\" aby otworzyć okno dialogowe gdzie można wybrać plik do wysłania. Użycie " +
            "przycisku \"Odbierz plik\" również spowoduje otworzenie okna dialogowego. Gdzie możemy wybrać miejsce zapisu odebranego pliku, oczywiście" +
            "po wisaniu jego nazwy oraz rozszeżenia(naza_pliku.nazwa_rozszerzenia)." +
            "\n\nUWAGA! Aby odebrać plik nadawca musi go wcześniej wysłać używając przycisku \"Wyślij plik\". Pruba odebrania pliku przed wysłaniem zakończy " +
            "się niepowodzeniem.";

    /**
     * Empty class constructor.
     */
    private Data(){}

    /**
     * Method that pass instance of Data class.
     * @return Instance of Data class.
     */
    public static Data getInstance(){
        if(data == null)
            return data = new Data();
        return data;
    }

    /**
     * Get IP number.
     * @return IP Number.
     */
    public String getIpName() {
        return ipName;
    }

    /**
     * Set IP Number.
     * @param ipName IP Number.
     */
    public void setIpName(String ipName) {
        this.ipName = ipName;
    }

    /**
     * Get socket number
     * @return Socket number.
     */
    public int getSocketName() {
        return socketName;
    }

    /**
     * Set socket number.
     * @param socketName Socket number.
     */
    public void setSocketName(int socketName) {
        this.socketName = socketName;
    }

    /**
     * Set file path.
     * @param direction File path.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Add new message from application.
     * @param msg New message from application.
     */
    public void setMsg(String msg){
        this.msg.add(msg);
    }

    /**
     * Get application instruction
     * @return Application instruction.
     */
    public String getHelp(){
        return help;
    }

    /**
     * Get messages from application.
     * @return All messages that program sent.
     */
    @Override
    public String toString() {
        String msg = "";
        for(String s: this.msg)
            msg = s+"\n"+msg;
        return msg;
    }
}
