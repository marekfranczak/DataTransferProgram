package org.example;

import java.io.*;
import java.net.Socket;

/**
 * Class responsible for receiving the file. This class contacts to class Server on second device.
 * @author Marek Frańczak
 * @since 1.0.0
 */
public class Client {

    /**
     * This object is client sockets (also called just "sockets"). A socket is an endpoint for communication between two machines.
     */
    private Socket sock;
    /**
     * Stream that will pass the file to the disc.
     */
    private FileOutputStream fos;
    /**
     * Object supported saving file on disc.
     */
    private BufferedOutputStream bos;
    /**
     * Path for place where file will be saved.
     */
    private String direction;
    /**
     * Host nane that is necessary to connect with server.
     */
    private String host;
    /**
     * Port number in target device.
     */
    private int port;

    /**
     * Class constructor that pass info about host and port target server and path for downloaded file.
     * @param host Host nane that is necessary to connect with server.
     * @param port Port number in target device.
     * @param direction Path for place where file will be saved.
     */
    public Client(String host, int port, String direction){
        this.host = host;
        this.port = port;
        this.direction = direction;
    }

    /**
     * Method that perform connecting to server and received file.
     */
    public void go(){
        try{
            sock = new Socket(host, port);
            Data.getInstance().setMsg("Lączę się..");

            DataInputStream dis = new DataInputStream(sock.getInputStream());
            int size = dis.readInt();
            dis.close();

            byte[] bytesarray = new byte[size+ Integer.BYTES];
            sock = new Socket(host, port);
            InputStream is = sock.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bis.read(bytesarray, 0, bytesarray.length);
            Data.getInstance().setMsg("Plik zajmie "+bytesarray.length+"[B] na dysku.");

            fos = new FileOutputStream(direction);
            bos = new BufferedOutputStream(fos);
            bos.write(bytesarray, 0, bytesarray.length);
            bos.flush();
            Data.getInstance().setMsg("Plik został pobrany i zapisany na dysku");

            fos.close();
            bos.close();
            sock.close();
        }  catch (IOException ioe){
            Data.getInstance().setMsg("Błąd połączenia z drugim urządzeniem");
            System.err.println(ioe);
        } catch (ArrayIndexOutOfBoundsException iobe) {
            Data.getInstance().setMsg("Plik był większy niż miejsce przeznaczone na jego zapis.");
            System.err.println(iobe);
        }
    }
}
