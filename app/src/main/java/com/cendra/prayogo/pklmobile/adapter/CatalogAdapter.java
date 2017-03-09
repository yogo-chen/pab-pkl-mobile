package com.cendra.prayogo.pklmobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cendra.prayogo.pklmobile.R;

/**
 * Created by Admin on 2/19/2017.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {
    private String[] productList;

    public CatalogAdapter(String[] productList) {
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_view, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String productName = this.productList[position];
        holder.getProductNameTextView().setText(productName);
    }

    @Override
    public int getItemCount() {
        return productList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.productNameTextView = (TextView) itemView.findViewById(R.id.product_card_view_name_edit_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), productNameTextView.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public TextView getProductNameTextView() {
            return this.productNameTextView;
        }
    }
}
