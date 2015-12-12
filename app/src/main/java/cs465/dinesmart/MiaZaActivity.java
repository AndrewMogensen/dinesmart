package cs465.dinesmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class MiaZaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mia_za);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // setTitle("Mia Za's");

        String [] pastaFood = {"Fettuccine Alfredo                        $6.00 797cal",
                                "Veggie Multi-Grain Rotini            $6.00 711cal",
                                "Baked Mac and Cheese              $5.00 944cal",
                                "Spaghetti With Meatballs           $6.00 764cal"};
        String [] paniniFood = {"Pesto Vegetarian                         $7.00 744cal",
                                "Classic Italian                               $7.00 768cal",
                                "Barbeque Chicken                        $7.00 678cal",
                                "Honey Ham                                   $7.00 810cal"};
        String [] pizzaFood = {"Hawaiian                                        $7.00 654cal",
                                "Tuscan White                                $7.00 882cal",
                                "Chicken Fajita                               $7.00 1019cal",
                                "Buffalo Chicken                            $7.00 984cal"};
        String [] saladFood = {"Traditional Greek                          $6.00 189cal",
                                "Garden Salad                                $6.00 486cal",
                                "Caesar Salad                                 $7.00 622cal",
                                "Strawberry Romaine                    $7.00 250cal"};

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
