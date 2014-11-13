package com.boletim.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.boletim.android.R;
import com.boletim.domain.BoletimNotasAnual;

public class BoletimListExpandableAdapter extends BaseExpandableListAdapter {

	private List<BoletimNotasAnual> boletimNotas = new ArrayList<BoletimNotasAnual>();
	private Activity context;

	public BoletimListExpandableAdapter(Activity context) {
		this.context = context;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return boletimNotas.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final BoletimNotasAnual boletim = boletimNotas.get(groupPosition);

		LayoutInflater inflater = context.getLayoutInflater();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_boletim, null);
		}

		TextView descricao = (TextView) convertView
				.findViewById(R.id.tvItemDescricao);
		descricao.setText(getStringResourceByName(boletim.getKeys().get(
				childPosition)));

		TextView valor = (TextView) convertView.findViewById(R.id.tvItemValor);
		valor.setText(boletim.getNotas().get(childPosition));

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return boletimNotas.get(groupPosition).getTotal();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return boletimNotas.get(groupPosition).getDisciplina();
	}

	@Override
	public int getGroupCount() {
		return boletimNotas.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String text = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.group_boletim, null);
		}
		TextView item = (TextView) convertView.findViewById(R.id.disciplina);
		item.setTypeface(null, Typeface.BOLD);
		item.setText(text);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	public List<BoletimNotasAnual> getBoletimNotas() {
		return boletimNotas;
	}

	public void setBoletimNotas(List<BoletimNotasAnual> boletimNotas) {
		this.boletimNotas = boletimNotas;
	}

	private String getStringResourceByName(String aString) {
		String packageName = context.getPackageName();
		int resId = context.getResources().getIdentifier(aString, "string",
				packageName);
		return context.getString(resId);
	}

}