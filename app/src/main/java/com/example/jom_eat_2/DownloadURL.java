package com.example.jom_eat_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadURL
{
    public String readTheUrl(String restaurantUrl) throws IOException
    {
        String data = "";
        InputStream iS = null;
        HttpURLConnection httpURLConnection = null;

        try
        {
            URL url = new URL(restaurantUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            iS = httpURLConnection.getInputStream();
            BufferedReader bR = new BufferedReader(new InputStreamReader(iS));
            StringBuffer sB = new StringBuffer();

            String line = "";

            while ((line = bR.readLine()) != null)
            {
                sB.append(line);
            }

            data = sB.toString();
            bR.close();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            iS.close();
            httpURLConnection.disconnect();
        }

        return data;
    }
}
