package com.shonejin.smartmarket;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.shonejin.smartmarket.Adpater.CouponAdapter;
import com.shonejin.smartmarket.Adpater.ProductAdapter;
import com.shonejin.smartmarket.Model.Coupon;
import com.shonejin.smartmarket.Model.Product;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;


public class CouponFragment extends Fragment {
    String url = RestClient.BASE_URL + "/coupons?user_id=" + User.user_id;
    List<Coupon> couponList = new ArrayList<Coupon>();
    String[] values = null;
    ListView listView;
    CouponAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);
        listView = (ListView) view.findViewById(R.id.couponlist);
        new HttpAsyncTask().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CouponDetailActivity.class);
                Coupon item = couponList.get(position);
                intent.putExtra("Coupon", item);
                startActivity(intent);
            }
        });
        return view;
    }
    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    private class HttpAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... urls) {

            return GET(url);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            try
            {
                couponList = new ArrayList<Coupon>();
                JSONArray jarr = new JSONArray(result);
                for (int i = 0; i < jarr.length(); i++)
                {
                    Coupon coupon = Coupon.fromJson(jarr.getJSONObject(i));
                    couponList.add(coupon);
                }
                Log.e("Coupon", couponList.toString());
                adapter = new CouponAdapter(getActivity(),
                        R.layout.coupon_item, couponList);
                listView.setAdapter(adapter);
            } catch (Exception e)
            {
                Log.i("JSON", e.getMessage());
                return;
            }
        }
    }
}
