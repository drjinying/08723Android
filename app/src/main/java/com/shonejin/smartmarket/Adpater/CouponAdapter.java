package com.shonejin.smartmarket.Adpater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shonejin.smartmarket.Model.Coupon;
import com.shonejin.smartmarket.Model.Product;
import com.shonejin.smartmarket.R;

import java.util.List;

/**
 * Created by oshinrawal on 8/4/16.
 */
public class CouponAdapter extends ArrayAdapter<Coupon> {
    Context context;

    public CouponAdapter(Context context, int resource, List<Coupon> objects) {
        super(context, resource, objects);
        this.context = context;
    }
    private class ViewHolder {
        TextView txtTitle;
        TextView txtDesc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Coupon rowItem = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.coupon_item, null);
            viewHolder = new ViewHolder();
            viewHolder.txtDesc = (TextView) convertView.findViewById(R.id.couponDetail);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.couponName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtDesc.setText(rowItem.description);
        viewHolder.txtTitle.setText(rowItem.coupon_id);

        return convertView;
    }
}
