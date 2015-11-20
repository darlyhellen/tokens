package com.darly.token.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.darly.token.R;
import com.darly.token.widget.NumberButton;

public class FlipButtonAdapter extends ParentAdapter<String> {

	public FlipButtonAdapter(List<String> data, int resID, Context context) {
		super(data, resID, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View HockView(int position, View view, ViewGroup parent, int resID,
			Context context, String t) {
		// TODO Auto-generated method stub
		NumberButton button;
		if (view == null) {
			button = new NumberButton(context, position);
			button.setTextSize(context.getResources().getDimension(
					R.dimen.textSize));
		} else {
			button = (NumberButton) view;
			button.setNumber(position);
		}

		return button;
	}

}
