package com.example.reminder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reminder.data.DatabaseHandlerToL;
import com.example.reminder.model.Location;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;

import ir.map.sdk_map.maps.MapView;
import ir.map.sdk_map.MapirStyle;

import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ROTATION_ALIGNMENT_VIEWPORT;


public class AddLocationFragment extends Fragment {

    static AddLocationFragment ourInstance;
    private Context context;
    public static FragmentTransaction transaction;

    MapboxMap map;
    Style mapStyle;
    MapView mapView;
    TextView title;
    LinearLayout top;
    SymbolManager sampleSymbol1;
    EditText Title;
    Button subbtn;

    public static synchronized AddLocationFragment getInstance() {
        if (ourInstance == null) {
            ourInstance = new AddLocationFragment();
        }
        return ourInstance;
    }
    LatLng samplePoint = new LatLng(35.732521, 51.422575);
    int sampleZoom = 10;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_location,
                container, false);
        findViews(view);

        title=getActivity().findViewById(R.id.title);
        title.setText("Location Alarm");
        title.setTextSize(25);




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
                        sampleSymbol1 =  addSymbolToMap();
                        map.addOnMapLongClickListener(new MapboxMap.OnMapLongClickListener() {
                            @Override
                            public boolean onMapLongClick(@NonNull LatLng point) {
                               // Toast.makeText(context, "Long Click\npoint: " + point.getLatitude() + "," + point.getLongitude(), Toast.LENGTH_SHORT).show();
                                samplePoint.setLatitude(point.getLatitude());
                                samplePoint.setLongitude(point.getLongitude());
                                sampleSymbol1.deleteAll();
                                sampleSymbol1= addSymbolToMap();
                                return false;
                            }
                        });


                    }
                });
            }
        });



        title=view.findViewById(R.id.titleAddLocation);
        subbtn=view.findViewById(R.id.subbtn_addLocation);
        subbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (!title.getText().toString().isEmpty()) {
                    DatabaseHandlerToL db = new DatabaseHandlerToL(getActivity());
                    Location tempLocatins = new Location();
                    tempLocatins.setTitle(title.getText().toString());
                    tempLocatins.setLatitude(String.valueOf(samplePoint.getLatitude()));
                    tempLocatins.setLongitude(String.valueOf(samplePoint.getLongitude()));
                    db.addlocations(tempLocatins);
                    Toast.makeText(context.getApplicationContext(), "The alarm Location was successfully add", Toast.LENGTH_LONG).show();
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.farmain,locationsFragment.getInstance());
                    transaction.commit();


                } else {
                    Toast.makeText(context.getApplicationContext(), "Please specify a title", Toast.LENGTH_LONG).show();
                }
            }


        });



        return view;
    }





    private void zoomToSpecificLocation() {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(samplePoint, sampleZoom));
    }
    private SymbolManager addSymbolToMap() {
        mapStyle.addImage("sample_image_id", getResources().getDrawable(R.drawable.mapbox_marker_icon_default));
// create symbol manager object
        SymbolManager sampleSymbolManager = new SymbolManager(mapView, map, mapStyle);

// set non-data-driven properties, such as:
        sampleSymbolManager.setIconAllowOverlap(true);
        sampleSymbolManager.setIconRotationAlignment(ICON_ROTATION_ALIGNMENT_VIEWPORT);
// Add symbol at specified lat/lon
        SymbolOptions sampleSymbolOptions = new SymbolOptions();
        sampleSymbolOptions.withLatLng(samplePoint);
        sampleSymbolOptions.withIconImage("sample_image_id");
        sampleSymbolOptions.withIconSize(1.0f);
// save created Symbol Object for later access
        Symbol sampleSymbol = sampleSymbolManager.create(sampleSymbolOptions);
        return sampleSymbolManager;
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
