package com.application.minime;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private int userCoins = 100; // Example coin amount
    private TextView coinBalance;
    private ListView shopItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        coinBalance = findViewById(R.id.coin_balance);
        shopItemsList = findViewById(R.id.shop_items_list);

        coinBalance.setText(String.format("%d,00", userCoins));

        List<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.ic_shoe, "Shirt", 50));
        items.add(new Item(R.drawable.ic_shoe, "Pants", 40));
        items.add(new Item(R.drawable.ic_shoe, "Panama", 70));
        items.add(new Item(R.drawable.ic_shoe, "Shoe", 45));

        ShopAdapter adapter = new ShopAdapter(this, items);
        shopItemsList.setAdapter(adapter);

        shopItemsList.setOnItemClickListener((parent, view, position, id) -> {
            Item item = (Item) parent.getItemAtPosition(position);
            Intent intent = new Intent(ShopActivity.this, ItemBuyingActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        });

        initializeBottomMenu();
    }

    private void initializeBottomMenu() {
        LinearLayout profileButton = findViewById(R.id.profile_button);
        LinearLayout friendsButton = findViewById(R.id.friends_button);
        LinearLayout dailyTasksButton = findViewById(R.id.daily_tasks_button);
        LinearLayout backpackButton = findViewById(R.id.backpack_button);
        LinearLayout settingsButton = findViewById(R.id.settings_button);

        profileButton.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        friendsButton.setOnClickListener(v -> startActivity(new Intent(this, FriendsActivity.class)));
        dailyTasksButton.setOnClickListener(v -> startActivity(new Intent(this, DailyTasksActivity.class)));
        backpackButton.setOnClickListener(v -> startActivity(new Intent(this, BackpackActivity.class)));
        settingsButton.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }

    private class ShopAdapter extends ArrayAdapter<Item> {
        public ShopAdapter(ShopActivity context, List<Item> items) {
            super(context, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop_item, parent, false);
            }

            Item item = getItem(position);

            ImageView itemImage = convertView.findViewById(R.id.item_image);
            TextView itemName = convertView.findViewById(R.id.item_name);
            TextView itemPrice = convertView.findViewById(R.id.item_price);
            ImageView itemBuyButton = convertView.findViewById(R.id.item_buy_button);

            itemImage.setImageResource(item.getImageResId());
            itemName.setText(item.getName());
            itemPrice.setText(String.format("%d RC", item.getPrice()));
            itemBuyButton.setOnClickListener(v -> {
                if (userCoins >= item.getPrice()) {
                    userCoins -= item.getPrice();
                    coinBalance.setText(String.format("%d,00", userCoins));
                    Toast.makeText(getContext(), "Item purchased!", Toast.LENGTH_SHORT).show();
                    // Add the item to the inventory
                } else {
                    Toast.makeText(getContext(), "Not enough coins!", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
    }
}
