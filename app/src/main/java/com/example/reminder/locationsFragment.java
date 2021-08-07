package com.example.reminder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.view.ViewPropertyAnimator;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reminder.model.Email1;
import com.example.reminder.model.Location;
import com.mapbox.android.core.location.LocationEngineResult;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.OnSymbolLongClickListener;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ROTATION_ALIGNMENT_VIEWPORT;

import ir.map.sdk_map.maps.MapView;
import ir.map.sdk_map.MapirStyle;

import java.util.ArrayList;
import java.util.List;

import ir.map.sdk_map.maps.MapView;


public class locationsFragment extends Fragment {


    static locationsFragment ourInstance;
    private Context context;
    public static FragmentTransaction transaction;
    FloatingActionButton add_locations;
    MapboxMap map;
    Style mapStyle;
    MapView mapView;
    TextView title;
    LinearLayout top;
    TextView about;
    private static ArrayList<Location> AclarmArrayList;





    public static synchronized locationsFragment getInstance() {
        if (ourInstance == null) {
            ourInstance = new locationsFragment();
        }
        return ourInstance;
    }
   private LatLng samplePoint1 = new LatLng(35.732521, 51.422575);
    int sampleZoom1 = 10;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_locations,
                container, false);
        findViews(view);

        title=getActivity().findViewById(R.id.title);
        title.setText("Location Alarm");
        title.setTextSize(25);

        top=view.findViewById(R.id.cart_liner_1_6);
        about=view.findViewById(R.id.title_about_location_6);

        add_locations= view.findViewById(R.id.add_locations);

        add_locations.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,AddLocationFragment.getInstance());
                transaction.commit();
            }


        });


        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                map = mapboxMap;
                map.setStyle(new Style.Builder().fromUri(MapirStyle.MAIN_MOBILE_VECTOR_STYLE), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        mapStyle = style;
                        zoomToSpecificLocation();
                        List<Location> alarmList = home.db3.getallLocation();
                        AclarmArrayList = new ArrayList<>();
                        for (Location alarm : alarmList) {
                            Log.d("MainActivity", "onCreate: " + alarm.getTitle());
                            samplePoint1.setLatitude(Float.parseFloat(alarm.getLatitude()));
                            samplePoint1.setLongitude(Float.parseFloat(alarm.getLongitude()));
                            addSymbolToMap(alarm.getTitle());
                            AclarmArrayList.add(alarm);
                        }





                    }
                });
            }
        });





        return view;
    }





    private void zoomToSpecificLocation() {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(samplePoint1, sampleZoom1));
    }
    private void addSymbolToMap(final String tilte) {
        mapStyle.addImage("sample_image_id", getResources().getDrawable(R.drawable.mapbox_marker_icon_default));
// create symbol manager object
        SymbolManager sampleSymbolManager = new SymbolManager(mapView, map, mapStyle);
        sampleSymbolManager.addClickListener(new OnSymbolClickListener() {
            @Override
            public void onAnnotationClick(Symbol symbol) {
                Toast.makeText(context, "This is CLICK_EVENT", Toast.LENGTH_SHORT).show();
            }
        });
        sampleSymbolManager.addLongClickListener(new OnSymbolLongClickListener() {
            @Override
            public void onAnnotationLongClick(Symbol symbol) {
               // Toast.makeText(context, "This is LONG_CLICK_EVENT", Toast.LENGTH_SHORT).show();
                top.animate().alpha(1).setStartDelay(1000).setDuration(1000);
                about.setText(tilte);


            }
        });
// set non-data-driven properties, such as:
        sampleSymbolManager.setIconAllowOverlap(true);
        sampleSymbolManager.setIconRotationAlignment(ICON_ROTATION_ALIGNMENT_VIEWPORT);
// Add symbol at specified lat/lon
        SymbolOptions sampleSymbolOptions = new SymbolOptions();
        sampleSymbolOptions.withLatLng(samplePoint1);
        sampleSymbolOptions.withIconImage("sample_image_id");
        sampleSymbolOptions.withIconSize(1.0f);
// save created Symbol Object for later access
        Symbol sampleSymbol = sampleSymbolManager.create(sampleSymbolOptions);
    }




    private void setData() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void findViews(View view){

    }
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    }
