package cs465.dinesmart;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubwayActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subway);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Subway");

        String [] breakfastFood = {"Bacon, Egg and Cheese","Egg and Cheese","Black Forest Ham, Egg and Cheese","Steak, Egg White and Cheese"};
        String [] beefFood = {"Meatball Marinara", "Roast Beef", "Steak and Cheese"};
        String [] chickenFood = {"Oven Roasted Chicken","Chicken and Bacon Ranch Melt","Sweet Onion Chicken Teriyaki"};
        String [] saladFood = {"Double Chicken Chopped Salad","Spicy Italian Salad","Black Forest Ham Salad","Veggie Delite Salad"};


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
