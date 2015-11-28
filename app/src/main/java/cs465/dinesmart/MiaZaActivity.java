package cs465.dinesmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class MiaZaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mia_za);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Mia Za's");

        String [] pastaFood = {"Fettuccine Alfredo","Veggie Multi-Grain Rotini","Baked Mac and Cheese","Spaghetti With Meatballs"};
        String [] paniniFood = {"Pesto Vegetarian", "Classic Italian", "Barbeque Chicken","Honey Ham"};
        String [] pizzaFood = {"Hawaiian","Tuscan White","Chicken Fajita","Buffalo Chicken"};
        String [] saladFood = {"Traditional Greek","Garden Salad","Caesar Salad","Strawberry Romaine"};

        ImageButton home_btn = (ImageButton) findViewById(R.id.home_button_miaZa);
        home_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MiaZaActivity.this, Home_Screen.class);
                startActivity(i);
            }
        });



        ArrayAdapter<String> pastaAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                pastaFood
        );
        ListView pastaList = (ListView) findViewById(R.id.listView);
        pastaList.setAdapter(pastaAdapter);

        ArrayAdapter<String> paniniAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                paniniFood
        );
        ListView paniniList = (ListView) findViewById(R.id.listView2);
        paniniList.setAdapter(paniniAdapter);


        ArrayAdapter<String> pizzaAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                pizzaFood
        );
        ListView pizzaList = (ListView) findViewById(R.id.listView3);
        pizzaList.setAdapter(pizzaAdapter);

        ArrayAdapter<String> saladAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                saladFood
        );
        ListView saladList = (ListView) findViewById(R.id.listView4);
        saladList.setAdapter(saladAdapter);

    }

}
