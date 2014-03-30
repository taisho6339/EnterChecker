package com.taishonet.enter.checker.ui;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.taishonet.enter.checker.R;
import com.taishonet.enter.checker.interfaces.CallBack;
import com.taishonet.enter.checker.interfaces.RunTask;
import com.taishonet.enter.checker.model.RequestTask;
import com.taishonet.enter.checker.utilities.UIUtil;

public class SearchPlaceActivity extends Activity {

	private Button mSearchButton;
	private Button mAddButton;
	private EditText mEditSearchBox;
	private SearchResultMapFragment mMapFragment;
	private String mAddressStr = "";
	private Address mPlace = null;
	private boolean mIsRegisterable = false;

	private RunTask mGetTask = new RunTask() {
		@Override
		public Object run() {
			List<Address> addressList = getLocation(mAddressStr);
			return addressList;
		}
	};

	@SuppressWarnings("unchecked")
	private CallBack mCallBack = new CallBack() {

		@Override
		public void doCallBack(Object resultData) {
			if (!(resultData instanceof List))
				return;
			List<Address> addressList = (List<Address>) resultData;
			if (addressList == null || addressList.size() <= 0) {
				UIUtil.pushToast(SearchPlaceActivity.this,R.string.network_get_search_error);
				return;
			}
			mPlace = addressList.get(0);
			setLocationToMap(addressList.get(0), mAddressStr);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_place_layout);
		OnClickListener listener = new ButtonClickListener();
		mSearchButton = (Button) findViewById(R.id.search_button);
		mAddButton = (Button) findViewById(R.id.search_register_button);
		mSearchButton.setOnClickListener(listener);
		mAddButton.setOnClickListener(listener);
		mEditSearchBox = (EditText) findViewById(R.id.search_word_edit);
		mMapFragment = new SearchResultMapFragment();
		setFragment();
	}

	private void setFragment() {
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.search_map_frame_content, mMapFragment);
		transaction.commit();
	}

	private void setLocationToMap(Address address, String locationName) {
		mMapFragment.setLocation(address, locationName);
	}

	private void closeKeyboard() {
		InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		manager.hideSoftInputFromWindow(mEditSearchBox.getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	private List<Address> getLocation(String str) {
		try {
			Geocoder geoCoder = new Geocoder(SearchPlaceActivity.this,
					Locale.JAPAN);
			List<Address> addressList = geoCoder.getFromLocationName(str, 1);
			return addressList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	private void postSearchPlace() {
		mAddressStr = mEditSearchBox.getText().toString();
		if (mAddressStr.equals("")) {
			mIsRegisterable = false;
			UIUtil.pushToast(this,R.string.network_get_search_error);
			return;
		}
		closeKeyboard();
		mIsRegisterable = true;
		RequestTask reqTask = new RequestTask(this, mGetTask, mCallBack);
		reqTask.execute();
	}

	private void registerPlace() {
		if (!mIsRegisterable){
			UIUtil.pushToast(this,R.string.search_place_register_error);
			return;
		}
		RegisterDialogFragment dialogFragment = RegisterDialogFragment.newInstance(mAddressStr, mPlace);
		dialogFragment.show(getFragmentManager(), "dialog");
	}

	class ButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.search_button:
				postSearchPlace();
				break;
			case R.id.search_register_button:
				registerPlace();
				break;
			default:
				break;
			}
		}
	}
}
