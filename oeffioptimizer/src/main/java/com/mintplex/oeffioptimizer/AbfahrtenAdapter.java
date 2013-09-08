package com.mintplex.oeffioptimizer;

import java.util.List;
import java.util.Map;

import com.mintplex.oeffioptimizer.model.Steige;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class AbfahrtenAdapter extends BaseExpandableListAdapter {

	Context ctx;
	LayoutInflater inflater;
	public AbfahrtenAdapter(Context ctx) {
		this.ctx = ctx;
		this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	List<String> linien;
	Map<String, List<Steige>> steige;
	
	public void setData(List<String> linien, Map<String, List<Steige>> steige) {
		this.linien = linien;
		this.steige = steige;
	}
	
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		String linie = linien.get(groupPosition);
		return steige.get(linie).get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return ((long)groupPosition) << 16 + childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
		}
		String linie = linien.get(groupPosition);
		List<Steige> ls = steige.get(linie);
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		Steige s = ls.get(childPosition);
		tv.setText("Richtung " + s.richtungName);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		List<Steige> l = steige.get(linien.get(groupPosition));
		if (l != null) return l.size();
		return 0;
	}
	
	

	@Override
	public Object getGroup(int groupPosition) {
		return linien.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return linien.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
		}
		TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
		String line = linien.get(groupPosition);
		tv.setText(line);
		ExpandableListView eLV = (ExpandableListView) parent;
		eLV.expandGroup(groupPosition);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
