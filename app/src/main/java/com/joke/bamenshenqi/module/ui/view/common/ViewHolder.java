package com.joke.bamenshenqi.module.ui.view.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
	private View mConvertView;
	private int mPosition;
	private final SparseArray mViews;

	private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
		super();
		this.mPosition = position;
		this.mViews = new SparseArray();
		this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		this.mConvertView.setTag(this);
	}

	public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
		ViewHolder v0 = null;
		if (convertView == null) {
			v0 = new ViewHolder(context, parent, layoutId, position);
		} else {
			v0 = (ViewHolder) convertView.getTag();
			v0.mPosition = position;
		}

		return v0;
	}

	public View getConvertView() {
		return this.mConvertView;
	}

	public int getPosition() {
		return this.mPosition;
	}

	public View getView(int viewId) {
		Object v0 = this.mViews.get(viewId);
		if (v0 == null) {
			View v0_1 = this.mConvertView.findViewById(viewId);
			this.mViews.put(viewId, v0_1);
		}

		return ((View) v0);
	}

	public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
		((ImageView) this.getView(viewId)).setImageBitmap(bm);
		return this;
	}

	public ViewHolder setImageResource(int viewId, int drawableId) {
		((ImageView) this.getView(viewId)).setImageResource(drawableId);
		return this;
	}

	public ViewHolder setText(int viewId, String text) {
		((TextView) this.getView(viewId)).setText(((CharSequence) text));
		return this;
	}
}
