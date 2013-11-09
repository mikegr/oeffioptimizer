package com.mintplex.oeffioptimizer;

import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FragmentArg;
import com.googlecode.androidannotations.annotations.SystemService;
import com.googlecode.androidannotations.annotations.ViewById;
import com.mintplex.oeffioptimizer.routing.Legs;
import com.mintplex.oeffioptimizer.routing.Mode;
import com.mintplex.oeffioptimizer.routing.Points;
import com.mintplex.oeffioptimizer.routing.Trip;

@EFragment(R.layout.fragment_displayroute)
public class DisplayRouteFragment extends AbstractFragment {

	public static final String TAG = "DISPLAY_ROUTE";

	public static DisplayRouteFragment create(Trip trip) {
		DisplayRouteFragment f = new DisplayRouteFragment_();
		Bundle b = new Bundle();
		b.putSerializable("trip", trip);
		f.setArguments(b);
		return f;
	}

	@FragmentArg
	Trip trip;

	@SystemService
	LayoutInflater inflater;

	@ViewById(R.id.fragment_displayroute_container)
	LinearLayout container;

	@ViewById(R.id.fragment_displayroute_from)
	TextView from;

	@ViewById(R.id.fragment_displayroute_to)
	TextView to;

	@AfterViews
	public void afterViews() {
		List<Legs> legs = trip.getLegs();

		String firstStop = legs.get(0).getPoints().get(0).getName();
		List<Points> lastPoints = legs.get(legs.size() - 1).getPoints();
		String lastStop = lastPoints.get(lastPoints.size() - 1).getName();

		from.setText(Html.fromHtml(getString(R.string.display_route_from,
				new Object[] { firstStop })));
		to.setText(Html.fromHtml(getString(R.string.display_route_to,
				new Object[] { lastStop })));

		for (int i = 0; i < legs.size(); i++) {
			Legs leg = legs.get(i);
			List<Points> points = leg.getPoints();
			Points startPoint = points.get(0);
			Mode mode = leg.getMode();
			addHeader(startPoint, mode, true);

			if (i < (legs.size() - 1)) {
				Legs next = legs.get(i + 1);

				View transferContainer = inflater.inflate(
						R.layout.displayroute_transfer, null);

				FrameLayout blockContainer = (FrameLayout) transferContainer
						.findViewById(R.id.displayroute_tranfer_back);

				View block = inflater.inflate(
						R.layout.displayroute_transferblock, null);
				Utils.t(block, R.id.displayroute_tranferblock_text,
						"Hinten einsteigen");

				c(block, R.id.displayroute_tranferblock_top, mode.getNumber());

				c(block, R.id.displayroute_tranferblock_bottom, next.getMode()
						.getNumber());

				blockContainer.addView(block);

				container.addView(transferContainer);

			} else {
				View endstation = inflater.inflate(
						R.layout.displayroute_endstation, null);
				flipper = (ViewFlipper) endstation
						.findViewById(R.id.displayroute_endstation_flipper);
				RadioGroup rg = (RadioGroup) endstation
						.findViewById(R.id.displayroute_endstation_position);

				rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						switch (checkedId) {
						case R.id.displayroute_endstation_front:
							flipper.setDisplayedChild(0);
							break;
						case R.id.displayroute_endstation_middle:
							flipper.setDisplayedChild(1);
							break;
						case R.id.displayroute_endstation_back:
							flipper.setDisplayedChild(2);
							break;
						}
					}
				});
				
				for(int p = 0; p < 3; p++) {
					LinearLayout box = (LinearLayout) inflater.inflate(R.layout.displayroute_endstation_block, null);
					switch(p) {
					case 0:
						a(box, "Liechtensteinstraße");
						a(box, "Hohenstaufengasse");
						con(box, "<b>40A</b> Richtung Döblinger Friedhof");
						break;
					case 1: 
						break;
					case 2:
						
						con(box, "<b>Lift</b>");
						
						a(box, "Währinger Straße");
						a(box, "Universitätsring/Universität");
						a(box, "Mölker Bastei");
						a(box, "Schottengasse");
						a(box, "Universitätsring");
						a(box, "Schottengasse");
						a(box, "Währinger Straße");
						
						con(box, "<b>37</b> Richtung Hohe Warte");
						con(box, "<b>38</b> Richtung Grinzing");
						con(box, "<b>40</b> Richtung Herbeckstraße");
						con(box, "<b>41</b> Richtung Pötzleinsdorf");
						con(box, "<b>42</b> Richtung Antonigasse");
						con(box, "<b>71</b> Richtung Zentralfriedhof, 3. Tor");
						con(box, "<b>D</b> Richtung Alfred-Adler-Straße");
						con(box, "<b>1</b> Richtung Stefan-Fadinger-Platz");
						con(box, "<b>43</b> Richtung Neuwaldegg");
						con(box, "<b>44</b> Richtung Dornbach, Güpferlingstraße");
						con(box, "<b>D</b> Richtung Beethovengasse");
						con(box, "<b>1</b> Richtung Prater Hauptallee");
						con(box, "Vienna Ring Tram");
						con(box, "<b>71</b> Richtung Börse");
						con(box, "<b>1A</b> Richtung Stephansplatz");
						con(box, "<b>N25</b> Richtung Großfeldsiedlung");
						con(box, "<b>N41</b> Richtung Pötzleinsdorf");
						con(box, "<b>N43</b> Richtung Neuwaldegg");
						con(box, "<b>N60</b> Richtung Maurer Hauptplatz");
						con(box, "<b>N66</b> Richtung Liesing");
						con(box, "<b>N38</b> Richtung Grinzing");

						
						break;
					}
					flipper.addView(box);
				}
				
				container.addView(endstation);

			}
			if (i == legs.size() - 1) {
				addHeader(leg.getLastPoint(), mode, false);
			}
		}

	}

	private void con(ViewGroup box, String name) {
		box.addView(tv(name));
	}

	private void a(ViewGroup box, String name) {
		box.addView(tv("Ausgang <b>" + name + "</b>"));
	}

	private TextView tv(String name) {
		TextView tv = new TextView(getActivity());
		tv.setText(Html.fromHtml(name));
		return tv;
	}

	ViewFlipper flipper;

	private void addHeader(Points point, Mode mode, boolean showDirection) {
		String destination = mode.getDestination();
		String line = mode.getNumber();
		String station = point.getName();

		View header = inflater.inflate(R.layout.displayroute_station, null);
		TextView lineTv = Utils.t(header, R.id.displayroute_station_line, line);
		lineTv.setBackgroundResource(getLineBackground(line));
		;
		lineTv.setTextColor(getResources().getColor(getLineTextColor(line)));
		Utils.t(header, R.id.displayroute_station_name, station);
		Utils.t(header, R.id.displayroute_station_direction, destination);
		View dir = header
				.findViewById(R.id.displayroute_station_direction_container);
		if (!showDirection) {
			dir.setVisibility(View.GONE);
		}
		container.addView(header);
	}

	public void c(View view, int resId, String line) {
		View found = view.findViewById(resId);
		int bg = getLineBackground(line);
		found.setBackgroundResource(bg);
	}

	public static int getLineBackground(String name) {
		if (name.startsWith("U1")) {
			return R.drawable.line_u1;
		}
		if (name.startsWith("U2")) {
			return R.drawable.line_u2;
		}
		if (name.startsWith("U3")) {
			return R.drawable.line_u3;
		}
		if (name.startsWith("U4")) {
			return R.drawable.line_u4;
		}
		if (name.startsWith("U6")) {
			return R.drawable.line_u6;
		}
		if (name.endsWith("A")) {
			return R.drawable.line_bus;
		}
		if (name.endsWith("B")) {
			return R.drawable.line_bus;
		}
		if (name.startsWith("S")) {
			return R.drawable.line_sbahn;
		}
		if (name.startsWith("N")) {
			return R.drawable.line_nightline;
		}
		return R.drawable.line_bim;
	}

	public static int getLineColor(String name) {
		if (name.startsWith("U1")) {
			return R.drawable.line_u1;
		}
		if (name.startsWith("U2")) {
			return R.drawable.line_u2;
		}
		if (name.startsWith("U3")) {
			return R.drawable.line_u3;
		}
		if (name.startsWith("U4")) {
			return R.drawable.line_u4;
		}
		if (name.startsWith("U6")) {
			return R.drawable.line_u6;
		}
		if (name.endsWith("A")) {
			return R.drawable.line_bus;
		}
		if (name.endsWith("B")) {
			return R.drawable.line_bus;
		}
		if (name.startsWith("S")) {
			return R.drawable.line_sbahn;
		}
		if (name.startsWith("N")) {
			return R.drawable.line_nightline;
		}
		return R.drawable.line_bim;
	}

	public static int getLineTextColor(String name) {
		if (name.startsWith("N")) {
			return R.color.nightline_fg;
		}
		if (name.endsWith("A") || name.endsWith("B")) {
			return R.color.black;
		}

		return R.color.white;
	}

}
