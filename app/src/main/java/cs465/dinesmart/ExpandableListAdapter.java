package cs465.dinesmart;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs465.dinesmart.MapsActivity;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private MapsActivity _ma;
    private List<String> restaurantsOriginal;
    private List<String> restaurantsCurrent;
    private List<String> _listHeaderImage;
    // child data in format of Restaurant name, menuItem list.
    private HashMap<String, List<RestMenuItem>> menuDataOriginal;
    private HashMap<String, List<RestMenuItem>> menuDataCurrent;

    public ExpandableListAdapter(Context context, MapsActivity ma, List<String> listDataHeader,
                                 List<String> listHeaderImage, HashMap<String, List<RestMenuItem>> listChildData) {

        this._context = context;

        this._ma = ma;

        this.restaurantsOriginal = new ArrayList<String>();
        restaurantsOriginal.addAll(listDataHeader);
        this.restaurantsCurrent = new ArrayList<String>();
        restaurantsCurrent.addAll(listDataHeader);

        this.menuDataOriginal = new HashMap<String, List<RestMenuItem>>();
        for (String rest : restaurantsOriginal) {
            List<RestMenuItem> newList = new ArrayList<RestMenuItem>();
            newList.addAll(listChildData.get(rest));
            menuDataOriginal.put(rest, newList);
        }
        this.menuDataCurrent = new HashMap<String, List<RestMenuItem>>();
        for (String rest : restaurantsOriginal) {
            List<RestMenuItem> newList = new ArrayList<RestMenuItem>();
            newList.addAll(listChildData.get(rest));
            menuDataCurrent.put(rest, newList);
        }
        this._listHeaderImage = listHeaderImage;
    }

    @Override
    public RestMenuItem getChild(int groupPosition, int childPosititon) {
        return this.menuDataCurrent.get(this.restaurantsCurrent.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final RestMenuItem childItem = getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        String itemName = childItem.getItemName();
        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        txtListChild.setText(itemName);

        String itemPrice = String.valueOf(childItem.getPrice());
        TextView txtItemPrice = (TextView) convertView.findViewById(R.id.lblItemPrice);
        txtItemPrice.setText("$" + itemPrice);

        String itemCalories = String.valueOf(childItem.getCalories());
        TextView txtItemCalories = (TextView) convertView.findViewById(R.id.lblItemCalories);
        txtItemCalories.setText(itemCalories + "cal");

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.menuDataCurrent.get(this.restaurantsCurrent.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.restaurantsCurrent.get(groupPosition);
    }

    public Object getFoodType(int groupPosition) {
        return this._listHeaderImage.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.restaurantsCurrent.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        String imagename = (String) getFoodType(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        ImageView imgListChild = (ImageView) convertView
                .findViewById(R.id.foodType);
        ImageView restPageNav = (ImageView) convertView.findViewById(R.id.restaurantPage);
        ImageView navButton = (ImageView) convertView.findViewById(R.id.navigationButton);

        if (imagename == "chinese") {
            imgListChild.setImageResource(R.drawable.chinese);
            restPageNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PandaExpressPageActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
            navButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    _ma.visibleMarkers("Panda Express");
                }
            });
        }
        if (imagename == "bread") {
            imgListChild.setImageResource(R.drawable.bread);
            restPageNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PaneraBreadActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
            navButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    _ma.visibleMarkers("Panera Bread");
                }
            });
        }
        if (imagename == "sandwich") {
            imgListChild.setImageResource(R.drawable.sandwich);
            restPageNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SubwayActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
            navButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    _ma.visibleMarkers("Subway");
                }
            });
        }
        if (imagename == "fastfood") {
            imgListChild.setImageResource(R.drawable.fastfood);
            restPageNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), McDonaldsActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
            navButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    _ma.visibleMarkers("McDonald's");
                }
            });
        }
        if (imagename == "italian") {
            imgListChild.setImageResource(R.drawable.italian);
            restPageNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), MiaZaActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
            navButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    _ma.visibleMarkers("Mia Za's");
                }
            });
        }

        return convertView;
    }

    public void filterData(List<cs465.dinesmart.MapsActivity.filter> filters, String query) {

        restaurantsCurrent.clear();
        menuDataCurrent.clear();

        for (String rest : restaurantsOriginal) {

            List<RestMenuItem> itemList = menuDataOriginal.get(rest);
            List<RestMenuItem> newList = new ArrayList<RestMenuItem>();
            for (RestMenuItem menuItem : itemList) {
                if (shouldAdd(filters, menuItem)) {
                    if (query.length() == 0 || menuItem.getItemName().toLowerCase().contains(query.toLowerCase()) ||
                            rest.toLowerCase().contains(query.toLowerCase())) {
                        newList.add(menuItem);
                    }
                }
            }
            if (newList.size() > 0) {
                restaurantsCurrent.add(rest);
                menuDataCurrent.put(rest, newList);
            }
        }
        notifyDataSetChanged();
    }

    public boolean shouldAdd(List<cs465.dinesmart.MapsActivity.filter> filters, RestMenuItem currentItem) {
        boolean retVal = true;
        for (cs465.dinesmart.MapsActivity.filter f: filters){
            if (f.name.contains("rotein")){
                if (f.lessThan && f.currentValue < currentItem.getProtein()) { retVal = false; }
                else if (!f.lessThan && f.currentValue > currentItem.getProtein()) {retVal = false;}
            }
            else if (f.name.contains("alories")){
                if (f.lessThan && f.currentValue < currentItem.getCalories()) { retVal = false; }
                else if (!f.lessThan && f.currentValue > currentItem.getCalories()) {retVal = false;}
            }
            else if (f.name.contains("rice")){
                if (f.lessThan && f.currentValue < currentItem.getPrice()) { retVal = false; }
                else if (!f.lessThan && f.currentValue > currentItem.getPrice()) {retVal = false;}
            }
        }
        return retVal;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

