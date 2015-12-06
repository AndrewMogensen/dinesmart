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

public class PaneraBreadActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_panera_bread);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //setTitle("Panera Bread");

        String [] soupFood = {"Baked Potato Soup","Broccoli Cheddar Soup","Low-Fat Vegetarian Black Bean Soup","Vegetarian Creamy Tomato Soup"};
        String [] bagelFood = {"Cranberry Walnut Bagel", "Pumpkin Pie Bagel", "Everything Bagel","French Toast Bagel"};
        String [] sandwichFood = {"Roasted Turkey and Avocado BLT","Sierra Turkey Sandwich","Asiago Steak Sandwich","Classic Grilled Cheese"};
        String [] saladFood = {"Thai Chicken Salad","Chicken Cobb With Avocado","Power Kale Caesar Salad With Chicken","Greek Salad"};


        ImageButton home_btn = (ImageButton) findViewById(R.id.home_button_panera);
        home_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(PaneraBreadActivity.this, Home_Screen.class);
                startActivity(i);
            }
        });


        ArrayAdapter<String> soupAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                soupFood
        );
        ListView soupList = (ListView) findViewById(R.id.listView);
        soupList.setAdapter(soupAdapter);

        ArrayAdapter<String> bagelAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                bagelFood
        );
        ListView bagelList = (ListView) findViewById(R.id.listView2);
        bagelList.setAdapter(bagelAdapter);


        ArrayAdapter<String> sandwichAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                sandwichFood
        );
        ListView sandwichList = (ListView) findViewById(R.id.listView3);
        sandwichList.setAdapter(sandwichAdapter);

        ArrayAdapter<String> saladAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                saladFood
        );
        ListView saladList = (ListView) findViewById(R.id.listView4);
        saladList.setAdapter(saladAdapter);
    }

}
