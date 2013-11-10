package com.mintplex.oeffioptimizer;

import java.sql.SQLException;
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
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mintplex.oeffioptimizer.model.Connections;
import com.mintplex.oeffioptimizer.model.Exitinfo;
import com.mintplex.oeffioptimizer.model.Haltestellen;
import com.mintplex.oeffioptimizer.model.Lift;
import com.mintplex.oeffioptimizer.model.Steige;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			DatabaseHelper helper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
			steigeDao = helper.getSteigeDao();
			haltestellenDao = helper
					.getHaltestellenDao();
			connDao = helper.getConnectionsDao();
			exitinfoDao = helper.getExitinfoDao();
			liftDao = helper.getLiftDao();
			
		} catch (Exception e) {
			Log.e("Set up DB failed", e);
		}
	}
	
	Dao<Steige, Integer> steigeDao;
	Dao<Haltestellen, Integer> haltestellenDao;
	Dao<Connections, Integer> connDao;
	Dao<Exitinfo, Integer> exitinfoDao;
	Dao<Lift, Integer> liftDao;
	
	
	@AfterViews
	public void afterViews() {
		try {
			setupUI();
		} catch (Exception e) {
			Log.e("setup UI failed", e);
		}
	}
		
	private static final String FK_STEIG_ID = "FK_STEIG_ID";
	private static final String SYMBOLS = "SYMBOLS";
	
	private void setupUI() throws SQLException {
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
			Points transferFrom = points.get(1);

			Mode mode = leg.getMode();
			addHeader(startPoint, mode, true);

			Steige fromSteig = steigFromPoint(transferFrom);

			if (i < (legs.size() - 1)) {
				Legs next = legs.get(i + 1);

				View transferContainer = inflater.inflate(
						R.layout.displayroute_transfer, null);

				Points transferTo = next.getPoints().get(0);

				Connections con = null;
				try {
					Steige toSteig = steigFromPoint(transferTo);

					con = connDao.queryBuilder().where()
							.eq("FK_STEIG_ID", Long.toString(fromSteig.id)).and()
							.eq("FK_TRANSFER_ID", Long.toString(toSteig.id))
							.queryForFirst();

				} catch (SQLException e) {
					Log.e("Find connection failed", e);
				}
				if (con != null) {
					if (con.isFront()) {
						FrameLayout blockContainer = (FrameLayout) transferContainer
								.findViewById(R.id.displayroute_tranfer_front);
						fillTranferBlock(blockContainer, "Vorne einsteigen",
								leg, next);
					}
					if (con.isMiddle()) {
						FrameLayout blockContainer = (FrameLayout) transferContainer
								.findViewById(R.id.displayroute_tranfer_middle);
						fillTranferBlock(blockContainer, "Mitte einsteigen",
								leg, next);

					}
					if (con.isBack()) {
						FrameLayout blockContainer = (FrameLayout) transferContainer
								.findViewById(R.id.displayroute_tranfer_back);
						fillTranferBlock(blockContainer, "Hinten einsteigen",
								leg, next);

					}
				}
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

				String steigId = Long.toString(fromSteig.id);

				List<Exitinfo> frontExitInfos = exitinfoDao.queryBuilder()
						.where().eq(FK_STEIG_ID, steigId).and()
						.like(SYMBOLS, "%V%").query();
				List<Exitinfo> middleExitInfos = exitinfoDao.queryBuilder()
						.where().eq(FK_STEIG_ID, steigId).and()
						.like(SYMBOLS, "%M%").query();
				List<Exitinfo> backExitInfos = exitinfoDao.queryBuilder()
						.where().eq(FK_STEIG_ID, steigId).and()
						.like(SYMBOLS, "%H%").query();

				List<Connections> frontConns = connDao.queryBuilder().where()
						.eq(FK_STEIG_ID, steigId).and().like(SYMBOLS, "%V%")
						.query();
				List<Connections> middleConns = connDao.queryBuilder().where()
						.eq(FK_STEIG_ID, steigId).and().like(SYMBOLS, "%M%")
						.query();
				List<Connections> backConns = connDao.queryBuilder().where()
						.eq(FK_STEIG_ID, steigId).and().like(SYMBOLS, "%H%")
						.query();

				List<Lift> frontLifts = liftDao.queryBuilder().where()
						.eq(FK_STEIG_ID, steigId).and().like(SYMBOLS, "%V%")
						.query();
				List<Lift> middleLifts = liftDao.queryBuilder().where()
						.eq(FK_STEIG_ID, steigId).and().like(SYMBOLS, "%M%")
						.query();
				List<Lift> backLifts = liftDao.queryBuilder().where()
						.eq(FK_STEIG_ID, steigId).and().like(SYMBOLS, "%H%")
						.query();

				for (int p = 0; p < 3; p++) {
					LinearLayout box = (LinearLayout) inflater.inflate(
							R.layout.displayroute_endstation_block, null);
					switch (p) {
					case 0:
						addLifts(box, frontLifts);
						addConnections(box, frontConns);
						addExitinfos(box, frontExitInfos);
						break;
					case 1:
						addLifts(box, middleLifts);
						addConnections(box, middleConns);
						addExitinfos(box, middleExitInfos);
						break;
					case 2:
						addLifts(box, backLifts);
						addConnections(box, backConns);
						addExitinfos(box, backExitInfos);
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

	private void addConnections(LinearLayout box, List<Connections> conns) {
		for (Connections con : conns) {
			Steige transfer = con.transferId;
			con(box, transfer.linienName + " " + transfer.richtungName);
		}
	}

	private void addLifts(LinearLayout box, List<Lift> lifts) {
		if (lifts.size() > 0) {
			con(box, "Lift");
		}
	}

	private void addExitinfos(LinearLayout box, List<Exitinfo> exitInfos) {
		for (Exitinfo ei : exitInfos) {
			a(box, ei.fkExitId.name);
		}
	}

	private void fillTranferBlock(FrameLayout blockContainer, String text,
			Legs leg, Legs next) {
		View block = inflater
				.inflate(R.layout.displayroute_transferblock, null);
		Utils.t(block, R.id.displayroute_tranferblock_text, text);

		c(block, R.id.displayroute_tranferblock_top, leg.getMode().getNumber());

		c(block, R.id.displayroute_tranferblock_bottom, next.getMode()
				.getNumber());

		blockContainer.addView(block);

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

	private Steige steigFromPoint(Points point) throws SQLException {
		Haltestellen haltestelle = haltestellenDao.queryForEq("DIVA",
				point.getRef().getId()).get(0);
		Steige steig = steigeDao.queryBuilder().where()
				.eq("FK_HALTESTELLEN_ID", haltestelle.id).and()
				.eq("STEIG", point.getRef().getPlatform())
				.queryForFirst();
		return steig;
	}
	
}
