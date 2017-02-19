package com.cendra.prayogo.pklmobile.adapter;

import android.content.ContentValues;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cendra.prayogo.pklmobile.R;
import com.cendra.prayogo.pklmobile.db.ProductDbHelper;

import java.util.List;

/**
 * Created by Admin on 2/19/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ContentValues> productList;

    public ProductAdapter(List<ContentValues> productList) {
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContentValues values = this.productList.get(position);
        holder.getProductNameTextView().setText((values.getAsString(ProductDbHelper.COLUMN_NAME_NAME)));
        holder.getSellPriceTextView().setText((values.getAsString(ProductDbHelper.COLUMN_NAME_SELL_PRICE)));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView sellPriceTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.productNameTextView = (TextView) itemView.findViewById(R.id.product_card_view_name_edit_text);
            this.sellPriceTextView = (TextView) itemView.findViewById(R.id.product_card_view_price_edit_text);
        }

        public TextView getProductNameTextView() {
            return this.productNameTextView;
        }

        public TextView getSellPriceTextView() {
            return sellPriceTextView;
        }
    }
}
