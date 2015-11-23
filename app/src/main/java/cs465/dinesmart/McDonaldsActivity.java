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

public class McDonaldsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mc_donalds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("McDonald's");

        String [] breakfastFood = {"Egg McMuffin","Hotcakes","Sausage Biscuit","Fruit 'N Yogurt Parfait"};
        String [] burgerFood = {"Big Mac", "Quarter Pounder", "Hamburger","BBQ Ranch Burger"};
        String [] chickenfishFood = {"McChicken","Chicken McNuggets","Premium McWrap Chicken and Ranch","Filet-O-Fish"};
        String [] saladFood = {"Premium Bacon Ranch Salad","Premium Southwest Salad","Side Salad"};


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
