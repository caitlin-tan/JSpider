package com.gog.spider;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Spider {
    public static void main(String[] args) {
        URL url = null;
        URLConnection urlConnection = null;
        PrintWriter pw = null;
        BufferedReader br = null;

        String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";
        Pattern pattern = Pattern.compile(regex);

        try {
            url = new URL("http://www.sina.com.cn/");
            urlConnection = url.openConnection();

            pw = new PrintWriter(new FileWriter("F:/workspace/spider/urls.txt"));
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String buf = null;
            while ((buf = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(buf);
                while (matcher.find()) {
                    pw.println(matcher.group());
                }
            }

            System.out.println("Successful!");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            pw.close();
        }
    }
}
