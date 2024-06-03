package com.application.minime;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BackpackAdapter extends RecyclerView.Adapter<BackpackAdapter.ViewHolder> {

    private final List<Item> items;
    private final Context context;

    public BackpackAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.backpack_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.itemIcon.setImageResource(item.getImageResId());
        holder.itemName.setText(item.getName());

        holder.itemDetailsButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            // Pass item details to ItemDetailActivity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemIcon;
        TextView itemName;
        Button itemDetailsButton;

        ViewHolder(View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.item_icon);
            itemName = itemView.findViewById(R.id.item_name);
            itemDetailsButton = itemView.findViewById(R.id.item_details_button);
        }
    }
}
