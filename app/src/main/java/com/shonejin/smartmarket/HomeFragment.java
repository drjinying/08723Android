package com.shonejin.smartmarket;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.shonejin.smartmarket.Adpater.ProductAdapter;
import com.shonejin.smartmarket.Model.Product;
import com.shonejin.smartmarket.R;
import com.shonejin.smartmarket.RowItem;

import org.json.JSONArray;
import org.json.JSONObject;

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

/**
 * Created by oshinrawal on 7/27/16.
 */
public class HomeFragment extends Fragment {
    private ListView listView;
    List<Product> ProductList;
    View view;
    ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.view = view;
        final EditText inputEditText = (EditText) view.findViewById(R.id.editText4);
        Button searchBtn = (Button) view.findViewById(R.id.button2);
        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                inputEditText.clearFocus();

                String keywords = inputEditText.getText().toString();

                progress = new ProgressDialog(getActivity());
                progress.setTitle("Searching");
                progress.setMessage("Wait while loading...");
                progress.show();

                new HttpAsyncTask().execute(RestClient.BASE_URL + "searchProducts?Keywords=" + keywords + "&SearchIndex=" + HomeActivity.catogory);
            }
        });

        listView = (ListView) view.findViewById(R.id.productList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                Product item = ProductList.get(position);
                intent.putExtra("Product", item);

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

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
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
                ProductAdapter adapter = new ProductAdapter(getActivity(),
                        R.layout.product_list_item, ProductList);
                listView.setAdapter(adapter);
                progress.dismiss();
            } catch (Exception e)
            {
                Log.i("JOSN", e.getMessage());
                return;
            }
        }
    }
}