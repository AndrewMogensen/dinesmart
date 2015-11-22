package cs465.dinesmart;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.graphics.Color;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MapsActivity extends ActionBarActivity implements OnMapReadyCallback,NavigationView.OnNavigationItemSelectedListener {

    private static GoogleMap mMap;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    List<String> listHeaderImage;
    HashMap<String, List<String>> listDataChild;

    SeekBarWithText seekBar;

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

        Context context = this;
        Drawable thumb = ContextCompat.getDrawable(context, R.drawable.calorie_lt_indicator);
        seekBar = new SeekBarWithText(context);
        seekBar.setOverlayText("Price");
        seekBar.setSubText("$10");
        seekBar.setMax(100);

        seekBar.setThumb(thumb);
        seekBar.setProgress(1);
        seekBar.setVisibility(View.VISIBLE);
        seekBar.setBackgroundColor(Color.TRANSPARENT);
        seekBar.setPadding(150, 0, 170, 0);

        LayoutParams lp = new LayoutParams(200, 200);
        seekBar.setLayoutParams(lp);
        lp.width = LayoutParams.MATCH_PARENT;
        lp.height = LayoutParams.WRAP_CONTENT;
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                System.out.println(".....111.......");

            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
                System.out.println(".....222.......");
            }

            public void onProgressChanged(SeekBar seekbar, int value, boolean arg2) {
                SeekBarWithText bar = (SeekBarWithText) seekbar;
                String val = "$" + value;
                bar.setSubText(val);
                ((TextView) findViewById(R.id.pricelt_subtext)).setText(val);
            }
        });
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

    private int getRelativeTop(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getTop();
        else
            return myView.getTop() + getRelativeTop((View) myView.getParent());
    }

    private int getRelativeLeft(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getLeft();
        else
            return myView.getLeft() + getRelativeLeft((View) myView.getParent());
    }

    public void updateSeekBar(SeekBar seekBar, MotionEvent event) {
        float pos = ((event.getX() - (getRelativeLeft(seekBar)+150))/(seekBar.getWidth()-(170+150)))*100;
        seekBar.setProgress((int) pos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // There's a giant relative view that's transparent, to make room for and hold the sliders.
        // This makes clicking on that view collapse the drawer, as expected.
        final RelativeLayout drawer = (RelativeLayout) findViewById(R.id.left_drawer);
        drawer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrawerLayout transparentFrame = (DrawerLayout) findViewById(R.id.drawer_layout);
                transparentFrame.closeDrawer(Gravity.LEFT);
            }
        });

        ImageButton indicatorButton = (ImageButton) findViewById(R.id.pricelt_button);

        indicatorButton.setOnTouchListener(new View.OnTouchListener() {
            RelativeLayout indicator;

            public boolean onTouch(View view, MotionEvent event) {
                // Keep the drawer slide-in mechanism from firing while sliding the seekbar
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.requestDisallowInterceptTouchEvent(true);

                LinearLayout indicators = (LinearLayout) findViewById(R.id.left_drawer_linear);

                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    // Hide the image
                    this.indicator = (RelativeLayout) indicators.findViewById(R.id.pricelt);
                    indicator.setVisibility(View.INVISIBLE);

                    // Add the seekbar and put it on top of the previous image
                    ((RelativeLayout) findViewById(R.id.left_drawer)).addView(seekBar);
                    seekBar.setX(getRelativeLeft(indicator) + 15);
                    seekBar.setY(getRelativeTop(indicator) - 70);

                    MapsActivity.this.updateSeekBar(seekBar, event); // Put seekbar at post 0
                } else if (event.getAction() == android.view.MotionEvent.ACTION_MOVE) {
                    MapsActivity.this.updateSeekBar(seekBar, event); // Put the seek bar in the thumb position
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    drawer.requestDisallowInterceptTouchEvent(false); // reenable slide to close
                    ((RelativeLayout) findViewById(R.id.left_drawer)).removeView(seekBar);
                    indicator.setVisibility(View.VISIBLE);
                }

                return true;
            }
        });

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
