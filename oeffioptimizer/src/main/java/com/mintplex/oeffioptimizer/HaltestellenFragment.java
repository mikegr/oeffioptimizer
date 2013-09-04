package com.mintplex.oeffioptimizer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mintplex.oeffioptimizer.model.Connections;
import com.mintplex.oeffioptimizer.model.Lift;
import com.mintplex.oeffioptimizer.model.Steige;

import java.util.List;

/**
 * Created by mike on 03.09.13.
 */
public class HaltestellenFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = getArguments().getLong("ID");
    }

    long id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station, container, false);

        LinearLayout steigeContainer = (LinearLayout) view.findViewById(R.id.fragment_station_container);

        List<Steige> steige = Steige.find(Steige.class, "fk_haltestellen_id = ?", Long.toString(id));


        for (Steige s: steige) {


            addSteig(inflater, steigeContainer, s);
        }
        return view;
    }

    private void addSteig(LayoutInflater li, LinearLayout steigeContainer, Steige s) {
        View view = li.inflate(R.layout.platform_header, steigeContainer, false);
        t(view, R.id.platform_header_line, s.linienName);
        t(view, R.id.platform_header_direction, s.richtungName);
        steigeContainer.addView(view);

        ViewGroup container = (ViewGroup) view.findViewById(R.id.platform_header_transfers);

        List<Connections> conns = Connections.find(Connections.class, "fk_steig_id = ? ", Long.toString(s.getId()));
        for(Connections c:conns) {
            View transfer = li.inflate(R.layout.tranfer_item, container, false);

            if (c.transferId != null) {
                t(transfer, R.id.tranfer_item_line, c.transferId.linienName);
                t(transfer, R.id.tranfer_item_direction, c.transferId.richtungName);
            }
            if (c.fkExit != null) {
                t(transfer, R.id.tranfer_item_exit, c.fkExit.name);
            }
            t(transfer, R.id.tranfer_item_symbols, c.symbols);
            t(transfer, R.id.tranfer_item_hint, c.hint);
            container.addView(transfer);
        }

    }


    public void t(View view, int tvRes, String txt) {
        TextView tv = (TextView) view.findViewById(tvRes);
        if (tv != null) {
            tv.setText(txt);
        }
    }

    public static HaltestellenFragment create(long id) {
        HaltestellenFragment f = new HaltestellenFragment();
        Bundle b = new Bundle();
        b.putLong("ID", id);
        f.setArguments(b);
        return f;
    }



}
