package com.taishonet.enter.checker.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.taishonet.enter.checker.R;
import com.taishonet.enter.checker.config.Config;
import com.taishonet.enter.checker.helper.PlaceDatabaseHelper;
import com.taishonet.enter.checker.interfaces.CallBack;
import com.taishonet.enter.checker.interfaces.RunTask;
import com.taishonet.enter.checker.model.RequestTask;
import com.taishonet.enter.checker.utilities.UIUtil;

public class RegisterDialogFragment extends DialogFragment {

	private Button mRegisterButton;
	private EditText mRadiusBox, mNameBox;
	private String mPlaceName = "";
	private int mRadius = 0;

	private RunTask mRegisterTask = new RunTask() {

		@Override
		public Object run() {

			PlaceDatabaseHelper helper = new PlaceDatabaseHelper(getActivity());
			SQLiteDatabase db = helper.getWritableDatabase();
			Bundle args = getArguments();
			ContentValues values = new ContentValues();
			values.put(Config.DB_KEY_PLACE_ADDRESS,
					args.getString(Config.EXTRA_KEY_PLACE_ADDRESS));
			values.put(Config.DB_KEY_PLACE_LATITUDE,
					args.getDouble(Config.EXTRA_KEY_PLACE_LATITUDE));
			values.put(Config.DB_KEY_PLACE_LONGITUDE,
					args.getDouble(Config.EXTRA_KEY_PLACE_LONGITUDE));
			values.put(Config.DB_KEY_PLACE_NAME, mPlaceName);
			values.put(Config.DB_KEY_PLACE_RADIUS, mRadius);
			db.insert(Config.DB_TABLE_NAME_PLACES, null, values);

			return null;
		}
	};

	private CallBack mCallBack = new CallBack() {

		@Override
		public void doCallBack(Object resultData) {
			UIUtil.pushToast(getActivity(),
					R.string.search_place_register_dialog_success);
		}
	};

	public static RegisterDialogFragment newInstance(String address,
			Address place) {
		Bundle bundle = new Bundle();
		bundle.putString(Config.EXTRA_KEY_PLACE_ADDRESS, address);
		bundle.putDouble(Config.EXTRA_KEY_PLACE_LATITUDE, place.getLatitude());
		bundle.putDouble(Config.EXTRA_KEY_PLACE_LONGITUDE, place.getLongitude());
		RegisterDialogFragment fragment = new RegisterDialogFragment();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.place_add_dialog_layout, null);
		mNameBox = (EditText) view.findViewById(R.id.add_dialog_name_box);
		mRadiusBox = (EditText) view.findViewById(R.id.add_dialog_radius_box);
		mRegisterButton = (Button) view
				.findViewById(R.id.search_register_add_button);
		mRegisterButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				registerPlace();
			}
		});
		return view;
	}

	private void registerPlace() {
		mPlaceName = mNameBox.getText().toString();
		String radiusStr = mRadiusBox.getText().toString();

		if (mPlaceName.equals("") || radiusStr.equals("")) {
			UIUtil.pushToast(getActivity(),
					R.string.search_place_register_dialog_error);
			return;
		}
		mRadius = Integer.parseInt(radiusStr);
		getDialog().dismiss();
		RequestTask task = new RequestTask(getActivity(), mRegisterTask,
				mCallBack);
		task.execute();
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.setTitle(R.string.search_register_title_text);
		return dialog;
	}

}
