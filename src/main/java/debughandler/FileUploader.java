package debughandler;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class FileUploader {

    public static String getTitle(String title) {
        String seperator = StringUtils.repeat('#', title.length() + 8);
        return seperator + "\n### " + title + " ###\n" + seperator + "\n\n\n";
    }

    public static void upload(ArrayList<LogHandler> handlers) throws Exception {
        handlers.add(new LogHandler("../ForgeModLoader-client-0"));
        String everything = "";
        for (LogHandler handler : handlers) {
            everything += getTitle(handler.name);

            FileInputStream inputStream = new FileInputStream("DebugHandler/" + handler.name + ".log");
            try {
                everything += IOUtils.toString(inputStream);
                everything += "\n\n\n";
            } finally {
                inputStream.close();
            }
        }

        URL url = new URL("http://hastebin.com/documents");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(everything);
        out.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
            decodedString = decodedString.replaceAll("\\{", "");
            decodedString = decodedString.replaceAll("}", "");
            decodedString = decodedString.replaceAll("\"", "");
            decodedString = decodedString.replaceAll(":", "");
            decodedString = decodedString.replaceAll("key", "");
            new Prompter(decodedString);
        }
        in.close();
    }
}
