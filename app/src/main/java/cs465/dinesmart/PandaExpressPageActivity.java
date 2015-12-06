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

        String [] appetizerFood = {"Chicken Egg Roll","Veggie Spring Roll","Cream Cheese Rangoon","Hot and Sour Soup"};
        String [] shrimpFood = {"Honey Walnut Shrimp", "Peppercorn Shrimp"};
        String [] beefFood = {"Beijing Beef","Kobari Beef","Broccoli Beef"};
        String [] chickenFood = {"Orange Chicken","Kung Pao Chicken","Mandarin Chicken","Sweetfire Chicken Breast"};

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
