package tracks.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;

public class Config {

    public static String getBaseUrl(){

        return "http://jach.com.pl:1337";

    }

    public static String user="thisisadmin";
    public static String password;

    static {
        try {
            password = getDecodedPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String path = "C:\\tests\\TracksApp\\Screenshots\\";

    /// IMPORT PASSWORD FROM FILE, HANDLE ENCODE/DECODE /// 
    private static String getEncodedPassword() throws IOException {
        BufferedReader reader;

        reader = new BufferedReader(new FileReader("tracks_cred.txt"));
        String line;
        line = reader.readLine();

        reader.close();

        return new String(Base64.getEncoder().encode(line.getBytes()));
    }

    private static String getDecodedPassword() throws IOException {
        return new String(Base64.getDecoder().decode(getEncodedPassword().getBytes()));
    }


}


