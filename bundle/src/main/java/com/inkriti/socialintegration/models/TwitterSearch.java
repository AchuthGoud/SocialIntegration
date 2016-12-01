package com.inkriti.socialintegration.models;

/**
 * Created by User on 30-11-2016.
 */
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
    public class TwitterSearch {

        private Logger logger = LoggerFactory.getLogger(TwitterSearch.class);
        // getting consumerKey  & consumerSecret from twitter application
        String consumerKey="pv1Fgt1Ct89GKTvaA1OqGD3Mu";
        String consumerSecret="K3oKmHvCgRVN5g31U9AK4JiniwTjvgrYy4jdAFrvDy69jrPl4I";

       // encode_64 consumerKey:consumerSecret
        String encodedStr = "cHYxRmd0MUN0ODlHS1R2YUExT3FHRDNNdTpLM29LbUh2Q2dSVk41ZzMxVTlBSzRKaW5pd1RqdmdyWXk0amRBRnJ2RHk2OWpyUGw0SQ==";
        private String resourceURL ="https://api.twitter.com/oauth2/token";
        private final String USER_AGENT = "Mozilla/5.0";
        // HTTP GET request
        public JSONArray getConnection(String tweets) throws Exception {
            //Twitter search api url
            String url = "https://api.twitter.com/1.1/search/tweets.json?q=%23"+tweets;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            // optional default is GET
            con.setRequestMethod("GET");
            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);
            //add Authorization Token type is Bearer
            con.setRequestProperty("Authorization","Bearer AAAAAAAAAAAAAAAAAAAAADibxgAAAAAA7aOL%2FSDuj8U3LtgzmNLyWQtZbns%3DM04OdGwhlUMr3QQBP4VgmhPkF5CwbZ9RznYHdPv9v7m2ZeEiaF");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
           // reading connection properties and response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print results
            // System.out.println(response.toString());

            //passing response to JSONObject
            JSONObject jsonObjectTweet = new JSONObject(response.toString());
            //getting statuses
            JSONArray jsonArrayTweet = new JSONArray(jsonObjectTweet.get("statuses").toString());
            return jsonArrayTweet;
        }
        // HTTP POST request
        private void sendPost() throws Exception {

        }

    }


