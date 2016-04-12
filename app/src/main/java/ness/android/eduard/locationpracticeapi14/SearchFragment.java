package ness.android.eduard.locationpracticeapi14;


import android.annotation.TargetApi;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {
    public Button btn;
    public EditText etSearch;
    public OnLocationInferListner inferListner;
    public SearchFragment() {
        // Required empty public constructor
    }

    public void setInferListner(OnLocationInferListner inferListner) {
        this.inferListner = inferListner;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        btn = (Button) container.findViewById(R.id.button);
        etSearch = (EditText) container.findViewById(R.id.editText);

        btn.setOnClickListener(this);
        return view;
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        String query = etSearch.getText().toString();

        Geocoder geocoder =new Geocoder(getContext());

        StringBuilder stringBuilder = new StringBuilder();

        try {
            LatLng latLng = new LatLng(1,1);
            List<Address> addresses = geocoder.getFromLocationName(query, 3);
            for (int i = 0; i < addresses.size(); i++) {
                Address address =addresses.get(i);
                stringBuilder.append(address.getAddressLine(i)).append("\n").
                        append(address.getLatitude()).append("\n").
                        append(address.getLongitude()).append("\n");
                        latLng = new LatLng(address.getLatitude(),address.getLongitude());
            }

            if(inferListner != null)
                inferListner.OnLocationInfer(latLng);
            Toast.makeText(getContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
