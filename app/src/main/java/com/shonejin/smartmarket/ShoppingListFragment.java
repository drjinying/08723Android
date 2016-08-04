package com.shonejin.smartmarket;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shonejin.smartmarket.Adpater.ProductAdapter;
import com.shonejin.smartmarket.Model.Product;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by oshinrawal on 7/27/16.
 */
public class ShoppingListFragment extends Fragment {
    String url = RestClient.BASE_URL + "/shoppingLists?user_id=" + User.user_id;
    View view;
    private ListView listView;
    List<Product> ProductList;
    ProductAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping_list, container, false);
        listView = (ListView) view.findViewById(R.id.shoppinglist);
        new GetShoppingList().execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShoppingListDetail.class);
                Product item = ProductList.get(position);
                intent.putExtra("Product", item);

                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private class GetShoppingList extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {
            return GET(url);
        }

        @Override
        protected void onPostExecute(String result) {
            try
            {
                ProductList = new ArrayList<Product>();
                JSONArray jarr = new JSONArray(result);
                for (int i = 0; i < jarr.length(); i++)
                {
                    Product product = Product.fromJson(jarr.getJSONObject(i));
                    ProductList.add(product);
                }
                adapter = new ProductAdapter(getActivity(),
                        R.layout.product_list_item, ProductList);
                listView.setAdapter(adapter);

            } catch (Exception e)
            {
                Log.i("JSON", e.getMessage());
            }
        }
    }
}
