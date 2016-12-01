
package com.inkriti.socialintegration.servlets;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by User on 04-11-2016.
 */

@SlingServlet(paths = "/bin/instagram/access", methods = "GET", metatype = true)
public class InstagramServlet extends SlingAllMethodsServlet {
    //getting clientID,clientSecret & redirectURI from instagram dev api
    String clientID="375f83492f0b4631a60bc0abbb5473c1";
    String clientSecret="025c8098d91248df8bdc02b02e46822a";
    String redirectURI="http://localhost:4502/bin/instagram/access";
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String code=request.getParameter("code");
/*
        String httpurl = "https://api.instagram.com/oauth/access_token/"
                + "client_id=" + clientID
                + "&client_secret=" + clientSecret
                + "&grant_type=authorization_code"
                + "&redirect_uri=" + redirectURI
                + "&code=" + code;

        URL url = new URL(httpurl);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("POST");

        OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream());
        response.getWriter().print(httpCon.getResponseCode()+" "+httpCon.getResponseMessage()+" "+httpurl);
*/

        HttpClient httpclient;
        HttpPost httppost ;

        ArrayList<NameValuePair> postParameters;
        httpclient = new DefaultHttpClient();
        //HttpPost url
        httppost = new HttpPost("https://api.instagram.com/oauth/access_token/");
        postParameters = new ArrayList<NameValuePair>();
        //passing parameters to array
        postParameters.add(new BasicNameValuePair("client_id", clientID));
        postParameters.add(new BasicNameValuePair("client_secret", clientSecret));
        postParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
        postParameters.add(new BasicNameValuePair("redirect_uri", redirectURI));
        postParameters.add(new BasicNameValuePair("code", code));
        //setting entity httppost
        httppost.setEntity(new UrlEncodedFormEntity(postParameters));
       //getting response
        HttpResponse response12 = httpclient.execute(httppost);
       // reading response
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response12.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        try {
            JSONObject jsonObject = new JSONObject(result.toString());
            JSONObject j1   =   new JSONObject(jsonObject.get("user").toString());
            response.getWriter().println(j1.get("full_name"));
            response.getWriter().println("---------------------------");
            response.getWriter().println(j1.get("profile_picture"));
            response.getWriter().println(result.toString());
            RequestDispatcher dispatcher = request.getRequestDispatcher(result.toString());
            dispatcher.include(request, response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("HelloPost");
    }
}
