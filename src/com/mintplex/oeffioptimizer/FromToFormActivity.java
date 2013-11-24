package com.mintplex.oeffioptimizer;

import java.net.URL;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.OptionsMenu;
import com.mintplex.oeffioptimizer.model.Haltestellen;
import com.mintplex.oeffioptimizer.routing.Routing;

@EActivity(R.layout.activity_default)
@OptionsMenu(R.menu.activity_main)
public class FromToFormActivity extends AbstractActivity {

	
	
	@AfterViews
	public void afterViews() {
		setFragment(new FromToFormFragment_(), FromToFormFragment.TAG);
		
	}

	public void searchRoute(final Haltestellen fromData, final Haltestellen toData) {
		
		final ProgressDialog dlg = ProgressDialog.show(this, "Lade Routen...", "Lade...");
		
		new AsyncTask<Void, Void, Routing>() {
			@Override
			protected Routing doInBackground(Void... params) {
				try {
					//String url = getString(R.string.url_routing, new Object[] {"60201018", "60201184"});
					
					String url = getString(R.string.url_routing, new Object[] {fromData.diva, toData.diva});
					String data = Utils.convertStreamToString(new URL(url).openConnection().getInputStream());
					Gson gson = new Gson();
					return gson.fromJson(data, Routing.class);
					
				} catch (Exception e) {
					Log.e("Failed to get routing info", e);
					return null;
				}
				
			}
			protected void onPostExecute(Routing result) {
				dlg.dismiss();
				if (result == null) {
					Toast.makeText(FromToFormActivity.this, "No routing data", Toast.LENGTH_LONG).show();
					return;
				}
				FromToFormFragment f = (FromToFormFragment) getSupportFragmentManager().findFragmentByTag(FromToFormFragment.TAG);
				if (f != null)  {
					f.showTrips(result);
				}
			};
		}.execute();
	}

	protected void showTrips(Routing result) {
		
	}
	
	@OptionsItem(R.id.menu_feedback)
	public void onFeedback() {
		Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"michael.greifeneder@gmail.com"});
        i.setType("text/message");
        i.putExtra(Intent.EXTRA_SUBJECT, "Feedback zu ÖffiOptimizer");
        i.putExtra(Intent.EXTRA_TEXT, "Mir gefällt.../Mir fehlt.../Falsche Daten bei Verbindung...\n\n");
        startActivity(Intent.createChooser(i, "Feedback geben"));   
	}

}
