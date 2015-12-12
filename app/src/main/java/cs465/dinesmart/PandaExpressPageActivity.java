package cs465.dinesmart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class PandaExpressPageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_panda_express_page);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //setTitle("Panda Express");

        String [] appetizerFood = {"Chicken Egg Roll                         $1.49 200cal",
                                    "Veggie Spring Roll                      $1.49 190cal",
                                    "Cream Cheese Rangoon            $1.49 190cal",
                                    "Hot and Sour Soup                      $1.49 120cal"};
        String [] shrimpFood = {"Honey Walnut Shrimp                $5.80 360cal",
                                "Peppercorn Shrimp                     $5.80 360cal"};
        String [] beefFood = {"Beijing Beef                                  $5.80 470cal",
                                "Kobari Beef                                  $6.80 310cal",
                                "Broccoli Beef                               $5.80 150cal"};
        String [] chickenFood = {"Orange Chicken                           $5.80 380cal",
                                "Kung Pao Chicken                       $5.80 290cal",
                                "Mandarin Chicken                       $5.80 300cal",
                                "Sweetfire Chicken Breast           $5.80 380cal"};

        ImageButton home_btn = (ImageButton) findViewById(R.id.home_button_panda);
        home_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(PandaExpressPageActivity.this, Home_Screen.class);
                startActivity(i);
            }
        });


        ArrayAdapter<String> appetizerAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                appetizerFood
        );
        ListView appetizerList = (ListView) findViewById(R.id.listView);
        appetizerList.setAdapter(appetizerAdapter);

        ArrayAdapter<String> shrimpAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                shrimpFood
        );
        ListView shrimpList = (ListView) findViewById(R.id.listView2);
        shrimpList.setAdapter(shrimpAdapter);


        ArrayAdapter<String> beefAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                beefFood
        );
        ListView beefList = (ListView) findViewById(R.id.listView3);
        beefList.setAdapter(beefAdapter);

        ArrayAdapter<String> chickenAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                chickenFood
        );
        ListView chickenList = (ListView) findViewById(R.id.listView4);
        chickenList.setAdapter(chickenAdapter);
    }

}
