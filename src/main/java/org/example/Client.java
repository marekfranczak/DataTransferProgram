package org.example;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket sock;
    private FileOutputStream fos;
    private BufferedOutputStream bos;
    private String direction;
    private String host;
    private int port;

    public Client(String host, int port, String direction){
        this.host = host;
        this.port = port;
        this.direction = direction;
    }

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
