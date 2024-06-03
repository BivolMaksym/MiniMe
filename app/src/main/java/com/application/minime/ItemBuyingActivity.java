package com.application.minime;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemBuyingActivity extends AppCompatActivity {

    private TextView itemName;
    private ImageView itemImage;
    private TextView itemCoinBoost;
    private TextView itemPassiveAbility;
    private TextView itemAdditionalInfo;
    private TextView itemActiveStatus;
    private TextView itemBreakChance;
    private TextView itemPrice;
    private Button buyButton;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_buying);

        itemName = findViewById(R.id.item_name);
        itemImage = findViewById(R.id.item_image);
        itemCoinBoost = findViewById(R.id.item_coin_boost);
        itemPassiveAbility = findViewById(R.id.item_passive_ability);
        itemAdditionalInfo = findViewById(R.id.item_additional_info);
        itemActiveStatus = findViewById(R.id.item_active_status);
        itemBreakChance = findViewById(R.id.item_break_chance);
        itemPrice = findViewById(R.id.item_price);
        buyButton = findViewById(R.id.buy_button);
        backButton = findViewById(R.id.back_button);

        // Example data, you should fetch this data dynamically
        itemName.setText("Shoe");
        itemImage.setImageResource(R.drawable.ic_shoe);
        itemCoinBoost.setText("Coin boost: 0.1x");
        itemPassiveAbility.setText("Passive ability: Increases chance of event 'speed up boost' by 10%");
        itemAdditionalInfo.setText("This item is active only when equipped. After completing quest this item might break with a chance of 4%.");
        itemActiveStatus.setText("Active status: Equipped");
        itemBreakChance.setText("Break chance: 4%");
        itemPrice.setText("70 RC");

        backButton.setOnClickListener(v -> finish());

        buyButton.setOnClickListener(v -> {
            // Handle item buying logic
        });
    }
}
