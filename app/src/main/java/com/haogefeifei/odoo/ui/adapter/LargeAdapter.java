package com.haogefeifei.odoo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haogefeifei.odoo.R;
import com.haogefeifei.odoo.view.BubbleTextGetter;

import java.util.List;

public final class LargeAdapter extends RecyclerView.Adapter<LargeAdapter.ViewHolder> implements BubbleTextGetter {

    private static final int SIZE = 1000;
    private final List<String> items;
    private Context mContext;

    public LargeAdapter(Context context) {

        this.mContext = context;
        this.items = java.util.Arrays.asList(mContext.getResources().getStringArray(R.array.partner_data));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_partner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = items.get(position);
        holder.setText(text);
    }

    @Override
    public String getTextToShowInBubble(final int pos) {
        return Character.toString(items.get(pos).charAt(0));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtPartnerName;

        private ViewHolder(View itemView) {
            super(itemView);
            this.txtPartnerName = (TextView) itemView.findViewById(R.id.txt_partner_name);
        }

        public void setText(CharSequence text) {
            txtPartnerName.setText(text);
        }
    }
}
