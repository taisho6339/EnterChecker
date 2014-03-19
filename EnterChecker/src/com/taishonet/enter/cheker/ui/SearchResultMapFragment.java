package com.taishonet.enter.cheker.ui;

import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SearchResultMapFragment extends MapFragment {

	private Marker mMarker;
	private GoogleMap mMap;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = super.onCreateView(inflater, container, savedInstanceState);
		mMap = getMap();
		return view;
	}

	private void setMapFocus(Address address) {
		mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition
				.builder()
				.target(new LatLng(address.getLatitude(), address
						.getLongitude())).zoom(17.0f).build()));
	}

	public void setLocation(Address address, String locationName) {
		if (mMarker == null) {
			mMarker = mMap.addMarker(new MarkerOptions().position(
					new LatLng(address.getLatitude(), address.getLongitude()))
					.title(locationName));
		} else {
			mMarker.setPosition(new LatLng(address.getLatitude(), address
					.getLongitude()));
			mMarker.setTitle(locationName);
		}
		setMapFocus(address);
	}
}
