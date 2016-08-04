package com.shonejin.smartmarket;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shonejin.smartmarket.R;
import com.shonejin.smartmarket.Model.Product;

import java.io.InputStream;

/**
 * Created by oshinrawal on 8/1/16.
 */


public class ProductDetailActivity extends AppCompatActivity {
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Product item = (Product) getIntent().getSerializableExtra("Product");
        Log.e("Item", item.title);

        TextView title = (TextView) findViewById(R.id.textView9);
        TextView price = (TextView) findViewById(R.id.textView10);
        Button location = (Button) findViewById(R.id.button3);
        WebView description = (WebView) findViewById(R.id.webView2);
        WebView rating = (WebView) findViewById(R.id.webView);
        WebView icon = (WebView) findViewById(R.id.webView3);
        location.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

            }
        });

        title.setText(item.title);
        price.setText("Price: " + item.price);
        if (item.x > 0 || item.y > 0)
        {
            location.setText("location: (" + item.x + "," + item.y + ")");
        }else
        {
            location.setText("out of storage");
            location.setEnabled(false);
        }
        icon.loadUrl(item.image);
        description.loadData(item.description, "text/html", Xml.Encoding.US_ASCII.name());
        rating.loadUrl(item.customReview);
    }
    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}
