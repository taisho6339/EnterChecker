package com.taishonet.enter.checker.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taishonet.enter.checker.R;

public class RegisterDialogFragment extends DialogFragment {

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.place_add_dialog_layout, null);
		
		return view;
	}
	
}
