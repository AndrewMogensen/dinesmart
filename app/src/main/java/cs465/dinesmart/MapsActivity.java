package cs465.dinesmart;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends ActionBarActivity implements OnMapReadyCallback,NavigationView.OnNavigationItemSelectedListener {

    private static GoogleMap mMap;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<String> listHeaderImage;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHeaderImage, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


    }
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



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in UIUC and move the camera
        LatLng uiuc = new LatLng(40.1105, -88.2284);
        mMap.addMarker(new MarkerOptions().position(uiuc).title("Marker in UIUC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(uiuc));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
    }
    private static void setUpMap()
    {
        mMap.setMyLocationEnabled(true);
        mMap.addMarker(new MarkerOptions().position(new LatLng(40.1105, -88.2284)).title("Marker in UIUC"));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
