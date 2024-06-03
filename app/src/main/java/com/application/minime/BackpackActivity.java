package com.application.minime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BackpackActivity extends AppCompatActivity {

    private RecyclerView inventoryList;
    private BackpackAdapter backpackAdapter;
    private List<Item> backpackItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack);

        inventoryList = findViewById(R.id.inventory_list);
        Button goShoppingButton = findViewById(R.id.go_shopping_button);

        backpackItems = new ArrayList<>();
        backpackAdapter = new BackpackAdapter(this, backpackItems);
        inventoryList.setLayoutManager(new LinearLayoutManager(this));
        inventoryList.setAdapter(backpackAdapter);

        loadBackpackItems();

        goShoppingButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShopActivity.class);
            startActivity(intent);
        });

        initializeBottomMenu();
    }

    private void loadBackpackItems() {
        backpackItems.add(new Item(R.drawable.ic_shoe, "Shirt", 50));
        backpackItems.add(new Item(R.drawable.ic_shoe, "Pants", 40));
        backpackItems.add(new Item(R.drawable.ic_shoe, "Panama", 70));
        backpackItems.add(new Item(R.drawable.ic_shoe, "Shoe", 45));

        backpackAdapter.notifyDataSetChanged();
    }

    private void initializeBottomMenu() {
        LinearLayout profileButton = findViewById(R.id.icon_profile);
        LinearLayout friendsButton = findViewById(R.id.icon_friends);
        LinearLayout dailyTasksButton = findViewById(R.id.icon_daily_tasks);
        LinearLayout backpackButton = findViewById(R.id.icon_backpack);
        LinearLayout settingsButton = findViewById(R.id.icon_settings);

        profileButton.setOnClickListener(view -> {
            // Intent to Profile Activity
        });

        friendsButton.setOnClickListener(view -> {
            // Intent to Friends Activity
        });

        dailyTasksButton.setOnClickListener(view -> {
            // Intent to Daily Tasks Activity
        });

        backpackButton.setOnClickListener(view -> {
            // Intent to Backpack Activity
            // This will stay in the same activity
        });

        settingsButton.setOnClickListener(view -> {
            // Intent to Settings Activity
        });
    }
}
