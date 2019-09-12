
package webcrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Discovery {

    private int num = 1;
    
    public void PegarImagens(String site) throws Exception {
        File arq = new File("html.txt");
        FileWriter fw = new FileWriter(arq);
        BufferedWriter bw = new BufferedWriter(fw);
        String html = getHTML(site);
        String regex = "http(s?)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./]*)+\\.(?:[gG][iI][fF]|[jJ][pP][gG]|[jJ][pP][eE][gG]|[pP][nN][gG]|[bB][mM][pP])";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(html);
        int i = 0;
        if (arq.exists()) {
            arq.createNewFile();
        }

        while (m.find()) {
            bw.write(m.group());
            saveImages(m.group());
            bw.flush();
            bw.newLine();
            i++;
            num = num +1;
        }

        bw.close();
        fw.close();

    }

    public void saveImages(String site) {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        String destino = "C:\\Users\\Pichau\\Documents\\NetBeansProjects\\WebCrawler\\test\\imagem";
        try {
            URL url = new URL(site);
            inputStream = url.openStream();
            outputStream = new FileOutputStream(destino + "imagem" + num + ".png");

            byte[] buffer = new byte[2048];
            int length;

            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException :- " + e.getMessage());

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException :- " + e.getMessage());

        } catch (IOException e) {
            System.out.println("IOException :- " + e.getMessage());

        }
    }

    private static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStreamReader isr = new InputStreamReader(conn.getInputStream());
        BufferedReader rd = new BufferedReader(isr);
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

}

