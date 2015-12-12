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

        String [] soupFood = {"Baked Potato Soup                       $4.99 340cal",
                            "Broccoli Cheddar Soup                 $4.99 330cal",
                            "Vegetarian Black Bean Soup       $4.99 230cal",
                            "Creamy Tomato Soup                   $4.99 450cal"};
        String [] bagelFood = {"Cranberry Walnut Bagel                $1.05 340cal",
                                "Pumpkin Pie Bagel                        $1.34 380cal",
                                "Everything Bagel                            $1.05 300cal",
                                "French Toast Bagel                       $1.34 350cal"};
        String [] sandwichFood = {"Roasted Turkey & Avocado BLT  $7.49 540cal",
                                "Sierra Turkey Sandwich                $6.99 730cal",
                                "Asiago Steak Sandwich                $7.99 810cal",
                                "Classic Grilled Cheese                  $6.99 580cal"};
        String [] saladFood = {"Thai Chicken Salad                        $8.59 490cal",
                                "Chicken Cobb With Avocado        $8.59 660cal",
                                "Kale Caesar Chicken Caesar        $7.99 600cal",
                                "Greek Salad                                    $7.49 370cal"};


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
