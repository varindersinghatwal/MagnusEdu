package com.magnus.edutech.webservices;

import android.content.Context;
import android.util.Log;

import com.magnus.edutech.App.GlobalConstants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WebServiceHandler {
    // GET REQUEST :
    public static String requestCallToServerGET(Context ctx, String url)
            throws URISyntaxException, IOException, JSONException {
        String responseString = null;

        if (GlobalConstants.DEBUG)
            Log.i("GET : ", "URL :-" + url);
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            responseString = EntityUtils.toString(httpEntity);

            if (GlobalConstants.DEBUG)
                Log.i("GET : ", "Response :-" + responseString);
        } catch (IOException ioe) {
            if (GlobalConstants.DEBUG)
                Log.e("GET 1 :", ioe.getMessage());
            ioe.printStackTrace();
        }
        return responseString;
    }

    // POST REQUEST :
    public static String requestACallToServerPOST(Context ctx, String url, List<NameValuePair> params)
            throws Exception {
        String serverResponse = null;
        if (GlobalConstants.DEBUG)
            Log.i("POST : ", "URL :-" + url + "\n JSON :- " + params);
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, GlobalConstants.TIMEOUT_MILLISEC);
        HttpConnectionParams.setSoTimeout(httpParams, GlobalConstants.TIMEOUT_MILLISEC);
        HttpClient httpClient = new DefaultHttpClient(httpParams);
        HttpPost request = new HttpPost(url);
        request.setEntity(new UrlEncodedFormEntity(params));
        try {
            HttpResponse httpResponse = httpClient.execute(request);
            HttpEntity httpEntity = httpResponse.getEntity();
            serverResponse = EntityUtils.toString(httpEntity);

            if (GlobalConstants.DEBUG)
                Log.i("POST : ", "Response :-" + serverResponse);

                /*if (serverResponse != null) {
                    String dataString = serverResponse.replace("&#039;", "\'");
                    dataString = dataString.replace("&039;", "\'");
                    jObject = new JSONObject(dataString.replace("&amp;", "&"));
                }*/

        } catch (IOException ioe) {
            if (GlobalConstants.DEBUG)
                Log.e("POST 1 :", ioe.getMessage());
            ioe.printStackTrace();
        }
        return serverResponse;
    }


}
