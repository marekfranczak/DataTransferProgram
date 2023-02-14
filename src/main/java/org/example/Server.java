package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Class responsible for sending the file. This class created server and waiting for class Client from second device.
 * Class create new threat which wait for connection.
 * @author Marek Frańczak
 * @since 1.0.0
 * @see Thread
 */
public class Server extends Thread {

    /**
     * This object is client sockets (also called just "sockets"). A socket is an endpoint for communication between two machines.
     */
    private Socket sock;
    /**
     * Port number in target device.
     */
    private int port;
    /**
     * File that will be sent to second device.
     */
    private FileInputStream fis;
    /**
     * Object supported sending file to second device.
     */
    private BufferedInputStream bis;
    /**
     * Stream that pass the file.
     */
    private OutputStream os;
    /**
     * Path for file which will be sent.
     */
    private String direction;
    /**
     * Stream that pass the size of sending file.
     */
    private DataOutputStream dos;

    /**
     * Class constructor that pass server port number and file path.
     * @param port Port number in target device.
     * @param direction Path for file which will be sent.
     */
    public Server(int port, String direction){
        this.direction = direction;
        this.port = port;
    }

    /**
     * Method that creates server site and is waiting for connecting from client. After connected method sends file.
     */
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
