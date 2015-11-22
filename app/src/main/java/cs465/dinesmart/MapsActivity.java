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
import android.view.LayoutInflater;
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
    HashMap<filter, View> filterIndicators;
    HashMap<filter, SeekBarWithText> filterSeekbars;

    class filter {
        String name;
        int maxValue;
        String prefix; // used for $
        String suffix; // used for 'g' in protein

        boolean lessThan;

        public filter(String name, int maxValue, String prefix, String suffix, boolean lessThan) {
            this.name = name;
            this.maxValue = maxValue;
            this.prefix = prefix;
            this.lessThan = lessThan;
            this.suffix = suffix;
        }
    }

    List<filter> filters;

    private void setupMapFragment() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setupListView() {
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHeaderImage, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    private void setupDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void addFilterIndicatorToLayout(final filter f, LinearLayout indicatorLayout) {
        // Inflate the indicator
        LayoutInflater inflater = (LayoutInflater)   this.getSystemService(LAYOUT_INFLATER_SERVICE);

        int indicatorTemplate = f.lessThan ? R.layout.lt_indicator : R.layout.gt_indicator;
        final View indicator = inflater.inflate(indicatorTemplate, indicatorLayout, false);

        // Set the text up
                ((TextView) indicator.findViewById(R.id.indicator_name)).setText(f.name);
        ((TextView) indicator.findViewById(R.id.sub_text)).setText(f.prefix + f.maxValue + f.suffix);

        // Add it to the layout
        indicatorLayout.addView(indicator, 0);

        filterIndicators.put(f, indicator);

        // Setup the button to reveal the slider
        ImageButton indicatorButton = (ImageButton) indicator.findViewById(R.id.button);
        indicatorButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                SeekBarWithText seekBar = filterSeekbars.get(f);

                // Keep the drawer slide-in mechanism from firing while sliding the seekbar
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.requestDisallowInterceptTouchEvent(true);

                LinearLayout indicators = (LinearLayout) findViewById(R.id.left_drawer_linear);

                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    // Hide the image
                    indicator.setVisibility(View.INVISIBLE);

                    // Add the seekbar and put it on top of the previous image
                    ((RelativeLayout) findViewById(R.id.left_drawer)).addView(seekBar);
                    seekBar.setX(getRelativeLeft(indicator) + 15);
                    seekBar.setY(getRelativeTop(indicator) - 70);

                    MapsActivity.this.updateDraggableFilterProgress(f, event); // Put seekbar at post 0
                } else if (event.getAction() == android.view.MotionEvent.ACTION_MOVE) {
                    MapsActivity.this.updateDraggableFilterProgress(f, event); // Put the seek bar in the thumb position
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    drawer.requestDisallowInterceptTouchEvent(false); // reenable slide to close
                    ((RelativeLayout) findViewById(R.id.left_drawer)).removeView(seekBar);
                    indicator.setVisibility(View.VISIBLE);
                }

                return true;
            }
        });
    }

    private void setupIndicators() {
        LinearLayout indicatorLayout = (LinearLayout) findViewById(R.id.left_drawer_linear);
        for (filter f : filters) {
            addFilterIndicatorToLayout(f, indicatorLayout);
        }
    }

    private void setupSeekbar(final filter f) {
        Context context = this;
        SeekBarWithText seekBar = new SeekBarWithText(context);
        seekBar.setOverlayText(f.name);
        seekBar.setSubText(f.prefix + f.maxValue + f.suffix);
        seekBar.setMax(f.maxValue);

        int thumbTemplate = f.lessThan ? R.drawable.lt_indicator_circle : R.drawable.gt_indicator_circle;
        Drawable thumb = ContextCompat.getDrawable(context, thumbTemplate);
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
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
            }

            public void onProgressChanged(SeekBar seekbar, int value, boolean arg2) {
                SeekBarWithText bar = (SeekBarWithText) seekbar; // casting seekbar so we can set text

                // Update the value on the bar
                String val = f.prefix + value + f.suffix;
                bar.setSubText(val);

                // Set the indicator to have the filter value
                ((TextView) filterIndicators.get(f).findViewById(R.id.sub_text)).setText(val);
            }
        });

        filterSeekbars.put(f, seekBar);
    }

    private void setupSeekbars() {
        for (filter f : filters) {
            setupSeekbar(f);
        }
    }

    private void setupFilters() {
        setupSeekbars();
        setupIndicators();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        filters = new ArrayList<filter>();
        filters.add(new filter("Protein", 80, "", "g", false));
        filters.add(new filter("Calories", 1500, "", "", true));
        filters.add(new filter("Price", 50, "$", "", true));

        filterIndicators = new HashMap<filter, View>();
        filterSeekbars = new HashMap<filter, SeekBarWithText>();

        setupMapFragment();
        setupListView();
        setupDrawer();
        setupFilters();
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

    public void updateDraggableFilterProgress(filter f, MotionEvent event) {
        SeekBar seekBar = filterSeekbars.get(f);
        float pos = ((event.getX() - (getRelativeLeft(seekBar)+150))/(seekBar.getWidth()-(170+150)))*f.maxValue;
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
