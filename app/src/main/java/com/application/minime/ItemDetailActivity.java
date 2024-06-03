package com.application.minime;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        ImageView backButton = findViewById(R.id.back_button);
        ImageView itemImage = findViewById(R.id.item_image);
        TextView itemName = findViewById(R.id.item_name);
        TextView itemCoinBoost = findViewById(R.id.item_coin_boost);
        TextView itemPassiveAbility = findViewById(R.id.item_passive_ability);
        TextView itemActiveStatus = findViewById(R.id.item_active_status);
        TextView itemBreakChance = findViewById(R.id.item_break_chance);

        // Set item details (these should be passed from the previous activity)
        itemImage.setImageResource(R.drawable.ic_shoe); // Replace with the correct image
        itemName.setText("Shoe");
        itemCoinBoost.setText("Coin boost: 0.1x");
        itemPassiveAbility.setText("Passive ability: Increases chance of event 'speed up boost' by 10%");
        itemActiveStatus.setText("This item is active only when equipped");
        itemBreakChance.setText("After completing quest this item might break with a chance of 4%");

        backButton.setOnClickListener(v -> finish());
    }
}
