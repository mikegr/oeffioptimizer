package com.mintplex.oeffioptimizer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mintplex.oeffioptimizer.model.Connections;
import com.mintplex.oeffioptimizer.model.Exitinfo;
import com.mintplex.oeffioptimizer.model.Lift;
import com.mintplex.oeffioptimizer.model.Steige;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    ExpandableListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_station, container, false);
        
        listView = (ExpandableListView) view.findViewById(R.id.fragment_station_list);

        HaltestellenAdapter adapter = new HaltestellenAdapter(getLayoutInflater(null));

        List<Steige> steige = Steige.find(Steige.class, "fk_haltestellen_id = ? ORDER BY fk_linien_id", Long.toString(id));
        Map<Steige, SteigInfo> adapterData = new HashMap<Steige, SteigInfo>();
        
        List<Steige> using = new ArrayList<Steige>();
        for (Steige s: steige) {
        	SteigInfo info = SteigInfoAdapter.getSteigInfo(s);
        	if (! s.linienName.startsWith("U")) continue;
        	adapterData.put(s, info);      
        	using.add(s);
        }
        adapter.setData(using, adapterData);
        listView.setAdapter(adapter);
        
        return view;
        
    }
    

    
    public static class SteigInfo {
    	public List<Connections> connections;
    	public List<Lift> lifts;
    	public List<Exitinfo> exits;
    	
		public int getCount() {
			return connections.size() + lifts.size() + exits.size();
		}

		public SugarRecord<?> getItem(int childPosition) {
			if (childPosition < connections.size()) {
				return connections.get(childPosition);
			}
			if (childPosition < connections.size() + exits.size()) {
				return exits.get(childPosition - connections.size());
			}
			
			if (childPosition < connections.size() + exits.size() + lifts.size()) {
				return lifts.get(childPosition - connections.size() - exits.size());
			}
			return null;
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
