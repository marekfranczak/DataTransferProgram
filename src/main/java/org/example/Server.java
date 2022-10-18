package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Server extends Thread {
    private Socket sock;
    private int port;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private OutputStream os;
    private String direction;
    private DataOutputStream dos;

    public Server(int port, String direction){
        this.direction = direction;
        this.port = port;
    }

    public void run(){
        while (true) {
            try{
                try {
                    ServerSocket serverSock = new ServerSocket(port);
                    serverSock.setSoTimeout(20000);
                    Data.getInstance().setMsg("Oczekiwanie na połączenie..");

                    sock = serverSock.accept();
                    Data.getInstance().setMsg("Zaakceptowano połączenie: " + sock.getInetAddress());

                    File myFile = new File(direction);
                    byte[] bytesarray = new byte[(int) myFile.length()];
                    dos = new DataOutputStream(sock.getOutputStream());
                    dos.writeInt((int) myFile.length());
                    dos.flush();
                    dos.close();

                    fis = new FileInputStream(myFile);
                    bis = new BufferedInputStream(fis);
                    bis.read(bytesarray, 0, bytesarray.length);

                    sock = serverSock.accept();
                    os = sock.getOutputStream();
                    os.write(bytesarray, 0, bytesarray.length);
                    os.flush();
                    Data.getInstance().setMsg("Wysłano plik: " + myFile.getName() + " o wielkości: " + bytesarray.length + "[B]");

                    bis.close();
                    os.close();
                    sock.close();
                    serverSock.close();
                    return;
                }catch (SocketTimeoutException ste){
                    Data.getInstance().setMsg("Przekroczono czas oczekiwania na klienta");
                    System.err.println(ste);
                    return;
                }
            } catch (IOException ioe){
                Data.getInstance().setMsg("Błąd wysyłania pliku");
                System.err.println(ioe);
                return;
            }
        }
    }
}
