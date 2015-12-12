package cs465.dinesmart;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.view.Window;

public class McDonaldsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mc_donalds);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // setTitle("McDonald's");

        String [] breakfastFood = {"Egg McMuffin                               $2.29 300cal",
                                    "Hotcakes                                       $2.29 350cal",
                                    "Sausage Biscuit                           $1.00 430cal",
                                    "Fruit 'N Yogurt Parfait                  $1.00 150cal"};
        String [] burgerFood = {"Big Mac                                         $3.99 540cal",
                                "Quarter Pounder                          $3.79 520cal",
                                "Hamburger                                   $2.49 240cal",
                                "BBQ Ranch Burger                       $4.09 610cal"};
        String [] chickenfishFood = {"McChicken                                    $1.00 370cal",
                                    "Chicken McNuggets                    $4.49 470cal",
                                    "McWrap Chicken and Ranch      $3.99 610cal",
                                    "Filet-O-Fish                                    $3.79 390cal"};
        String [] saladFood = {"Premium Bacon Ranch Salad     $4.59 230cal",
                                "Premium Southwest Salad         $4.79 290cal",
                                "Side Salad                                     $1.99 160cal"};


        ImageButton home_btn = (ImageButton) findViewById(R.id.home_button_mcdonalds);
                home_btn.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                                Intent i = new Intent(McDonaldsActivity.this, Home_Screen.class);
                                startActivity(i);
                            }
                    });


        ArrayAdapter<String> breakFastAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                breakfastFood
        );
        ListView breakfastList = (ListView) findViewById(R.id.listView);
        breakfastList.setAdapter(breakFastAdapter);

        ArrayAdapter<String> burgerAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                burgerFood
        );
        ListView burgerList = (ListView) findViewById(R.id.listView2);
        burgerList.setAdapter(burgerAdapter);


        ArrayAdapter<String> chickenfishAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                chickenfishFood
        );
        ListView chickenfishList = (ListView) findViewById(R.id.listView3);
        chickenfishList.setAdapter(chickenfishAdapter);

        ArrayAdapter<String> saladAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                saladFood
        );
        ListView saladList = (ListView) findViewById(R.id.listView4);
        saladList.setAdapter(saladAdapter);

    }

}
