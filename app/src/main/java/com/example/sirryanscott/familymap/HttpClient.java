package com.example.sirryanscott.familymap;

import com.example.sirryanscott.familymap.Login.LoginData;
import com.example.sirryanscott.familymap.Model.FamilyMapData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sirryanscott on 7/27/16.
 */
public class HttpClient {
    private URL url;
    private String urlString;
    private String postData;

    public HttpClient(){
        urlString = "http://" + LoginData.getInstance().getUrl();

        postData = "{" +
                "username:\"" + LoginData.getInstance().getCurrentUser().getUserName() + "\", " +
                "password:\"" + LoginData.getInstance().getCurrentUser().getPassword() + "\"" +
                "}";
    }

    public boolean login(){
        try {
            url = new URL(urlString + "/user/login");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            connection.connect();

            OutputStream requestBody = connection.getOutputStream();

            requestBody.write(postData.getBytes());
            requestBody.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Get response body input stream
                InputStream responseBody = connection.getInputStream();

                // Read response body bytes
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = responseBody.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                }

                // Convert response body bytes to a string
                String responseBodyData = baos.toString();

                try {
                    JSONObject jsonObject = new JSONObject(responseBodyData);

                    String result = jsonObject.getString("Authorization");

                    if(jsonObject.has("message")){
                        return false;
                    }
                    else {
                        LoginData.getInstance().getCurrentUser().setAuthorization(result);
                        LoginData.getInstance().getCurrentUser().setPersonId(jsonObject.getString("personId"));
                        return true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }

            }
            else{
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean peopleData(){
        try {
            url = new URL(urlString + "/person/");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", LoginData.getInstance().getCurrentUser().getAuthorization());
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Get response body input stream
                InputStream responseBody = connection.getInputStream();

                // Read response body bytes
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = responseBody.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                }

                // Convert response body bytes to a string
                String responseBodyData = baos.toString();

                try {
                    JSONObject jsonObject = new JSONObject(responseBodyData);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    boolean result = FamilyMapData.getInstance().buildFamilyMapPeopleData(jsonArray);
                    if(!result){
                        return false;
                    }
                    else {
                        return true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }

            }
            else{
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean eventData(){
        try {
            url = new URL(urlString + "/event/");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", LoginData.getInstance().getCurrentUser().getAuthorization());
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Get response body input stream
                InputStream responseBody = connection.getInputStream();

                // Read response body bytes
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = responseBody.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                }

                // Convert response body bytes to a string
                String responseBodyData = baos.toString();

                try {
                    JSONObject jsonObject = new JSONObject(responseBodyData);

                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    boolean result = FamilyMapData.getInstance().buildFamilyMapEventData(jsonArray);
                    if(!result){
                        return false;
                    }
                    else {
                        return true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }

            }
            else{
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
