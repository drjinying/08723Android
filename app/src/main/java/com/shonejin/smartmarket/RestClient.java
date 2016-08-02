package com.shonejin.smartmarket;
import com.loopj.android.http.*;

/**
 * Created by shone on 7/31/16.
 */
public class RestClient {
    private static AsyncHttpClient client = new AsyncHttpClient();
    public static final String BASE_URL = "http://ec2-52-87-235-234.compute-1.amazonaws.com:8080/";

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
