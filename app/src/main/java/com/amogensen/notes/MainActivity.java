package com.amogensen.notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<String> listHeaderImage;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHeaderImage, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listHeaderImage = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Panda Express");
        listDataHeader.add("Panera Bread");
        listDataHeader.add("Subway");
        listDataHeader.add("McDonalds");
        listDataHeader.add("Mia Za's");

        listHeaderImage.add("chinese");
        listHeaderImage.add("bread");
        listHeaderImage.add("sandwich");
        listHeaderImage.add("fastfood");
        listHeaderImage.add("italian");

        // Adding child data
        List<String> panda = new ArrayList<String>();
        panda.add("Orange Chicken");
        panda.add("General Tso's Chicken");
        panda.add("Fried Rice");
        panda.add("Chow Mein");

        List<String> panera = new ArrayList<String>();
        panera.add("Chicken Ceasar Salad");
        panera.add("Broccoli Cheddar Soup");
        panera.add("Sourdough Loaf");

        List<String> subway = new ArrayList<String>();
        subway.add("BLT");
        subway.add("Spicy Italian");
        subway.add("Meatball Sub");

        List<String> mcD = new ArrayList<String>();
        mcD.add("Big Mac");
        mcD.add("10-piece McNuggent");
        mcD.add("Large Fries");

        List<String> miaz = new ArrayList<String>();
        miaz.add("Cheese Pizza");
        miaz.add("Fettuccine Alfredo");
        miaz.add("BBQ Chicken Sandwich");

        listDataChild.put(listDataHeader.get(0), panda); // Header, Child data
        listDataChild.put(listDataHeader.get(1), panera);
        listDataChild.put(listDataHeader.get(2), subway);
        listDataChild.put(listDataHeader.get(3), mcD);
        listDataChild.put(listDataHeader.get(4), miaz);
    }
}