package org.example;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static Data data;
    private List<String> msg = new ArrayList<>();
    private String ipName, direction;
    private int socketName = 0;
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

    private Data(){}

    public static Data getInstance(){
        if(data == null)
            return data = new Data();
        return data;
    }

    public String getIpName() {
        return ipName;
    }

    public void setIpName(String ipName) {
        this.ipName = ipName;
    }

    public int getSocketName() {
        return socketName;
    }

    public void setSocketName(int socketName) {
        this.socketName = socketName;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setMsg(String msg){
        this.msg.add(msg);
    }

    public String getHelp(){
        return help;
    }

    @Override
    public String toString() {
        String msg = "";
        for(String s: this.msg)
            msg = s+"\n"+msg;
        return msg;
    }
}
