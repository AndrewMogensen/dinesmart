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

public class SubwayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_subway);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //setTitle("Subway");

        String [] breakfastFood = {"Bacon, Egg and Cheese               $3.50 440cal",
                                    "Egg and Cheese                           $3.50 360cal",
                                    "Black Forest Ham, Egg, Cheese $3.50 390cal",
                                    "Steak, Egg White and Cheese     $3.50 390cal"};
        String [] beefFood = {"Meatball Marinara                        $3.75 480cal",
                            "Roast Beef                                     $4.75 320cal",
                            "Steak and Cheese                        $4.75 380cal"};
        String [] chickenFood = {"Oven Roasted Chicken                 $4.25 320cal",
                                "Chicken and Bacon Ranch Melt  $4.75 610cal",
                                "Sweet Onion Chicken Teriyaki    $4.75 370cal"};
        String [] saladFood = {"Double Chicken Chopped Salad $7.50 220cal",
                                "Spicy Italian Salad                        $6.00 390cal",
                                "Black Forest Ham Salad              $6.00 110cal",
                                "Veggie Delite Salad                       $5.00 60cal"};

        ImageButton home_btn = (ImageButton) findViewById(R.id.home_button_subway);
        home_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(SubwayActivity.this, Home_Screen.class);
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

        ArrayAdapter<String> beefAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                beefFood
        );
        ListView beefList = (ListView) findViewById(R.id.listView2);
        beefList.setAdapter(beefAdapter);


        ArrayAdapter<String> chickenAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                chickenFood
        );
        ListView chickenList = (ListView) findViewById(R.id.listView3);
        chickenList.setAdapter(chickenAdapter);

        ArrayAdapter<String> saladAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                saladFood
        );
        ListView saladList = (ListView) findViewById(R.id.listView4);
        saladList.setAdapter(saladAdapter);
    }

}
