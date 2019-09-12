package webcrawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class WebCrawler {

    public static Buffer buffer = new Buffer();    
    
    public static void LerArquivo(String nome) throws IOException, Exception {

        File arq = new File(nome);
        FileReader fileR = null;
        BufferedReader bufferR = null;
        String readLine = "";
        Discovery discovery = new Discovery();
        ThreadUrl thread;
        if (arq.exists()) {
            fileR = new FileReader(arq);
            bufferR = new BufferedReader(fileR);
            while (readLine != null) {
                readLine = bufferR.readLine();
                if (readLine == null) {
                    break;
                }
                discovery.PegarImagens(readLine);
                //thread = new ThreadUrl(readLine);
                //thread.start();
            }
            bufferR.close();
            fileR.close();

        } else {
            arq.createNewFile();
        }

    }

    public static void main(String[] args) throws IOException, Exception {
        Observador observer = new Observador();
        buffer.attach(observer);
        LerArquivo("url.txt");
    }
}


