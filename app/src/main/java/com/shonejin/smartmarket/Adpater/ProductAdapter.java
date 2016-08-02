package com.shonejin.smartmarket.Adpater;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shonejin.smartmarket.R;
import com.shonejin.smartmarket.Model.Product;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oshinrawal on 7/28/16.
 */

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

public class ProductAdapter extends ArrayAdapter<Product> {

    Context context;

    public ProductAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    private class ViewHolder {
        WebView imageView;
        TextView txtTitle;
        TextView txtDesc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Product rowItem = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.product_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            viewHolder.imageView = (WebView) convertView.findViewById(R.id.icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtDesc.setText(rowItem.price);
        viewHolder.txtTitle.setText(rowItem.title);
        viewHolder.imageView.loadUrl(rowItem.image);

        return convertView;
    }
}

