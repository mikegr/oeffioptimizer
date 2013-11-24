/**
 * ÖffiOptimizer
 * Copyright (C) 2013 Michael Greifeneder
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package com.mintplex.oeffioptimizer;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FragmentArg;
import com.googlecode.androidannotations.annotations.ViewById;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

@EFragment
public class AbstractFragment extends Fragment {

	@FragmentArg("id")
	protected long id;
	
	
	@ViewById(R.id.adView)
	AdView adView;
	
	String[] testDevices = new String[] {
			AdRequest.DEVICE_ID_EMULATOR,
			"17E48B7FE47F7994389175A73AD3D232",
		    "3C31D05F6782DC3E802B3D17DDDFB677"
	};
	
	public void initAd() {
		AdRequest adRequest = addTestDevices(new AdRequest.Builder()).build();;
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdFailedToLoad(int errorCode) {
				super.onAdFailedToLoad(errorCode);
				adView.setVisibility(View.GONE);
			}
		});
	    adView.loadAd(adRequest);
	}
	
	private Builder addTestDevices(Builder builder) {
		for(String td:testDevices) {
			builder.addTestDevice(td);
		}
		return builder;
	}

	private InterstitialAd interstitial;
	 
	public void loadInterstital() {
		// Create the interstitial.
	    interstitial = new InterstitialAd(getActivity());
	    interstitial.setAdUnitId("ca-app-pub-5589682168196980/5808914558");
	
	    // Create ad request.
	    AdRequest adRequest = addTestDevices(new AdRequest.Builder()).build();;

	    // Begin loading your interstitial.
	    interstitial.loadAd(adRequest);
		
	}
	
	public void displayInterstitial() {
		if (interstitial.isLoaded()) {
		      interstitial.show();
		    }
	}
	@Override
	public void onPause() {
		if (adView != null) {
			adView.pause();	
		}
		super.onPause();
	}

	@Override
	public void onResume() {
	  super.onResume();
	  if (adView != null) {
		  adView.resume();  
	  }
	}

	@Override
	public void onDestroy() {
		if (adView != null) {
			adView.destroy();	
		}
	  super.onDestroy();
	}
}
