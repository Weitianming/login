package com.example.fragment;

import com.example.login.R;
import com.example.main.InitActivity;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class View3 extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.init_fragment3,
				container, false);
		
		
		Button main = (Button)rootView.findViewById(R.id.main);
		main.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new InitActivity().sp.edit().putBoolean("init", true).commit();
				getActivity().startActivity(new Intent("com.example.login.LoginActivity"));
			}
		});
		return rootView;
		
	}
	
}
