package com.joke.bamenshenqi.component.view;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xiaomage.edit.R;

public class FilterPopupWindowTools implements AdapterView.OnItemClickListener {
	public interface MyOnItemClick {
		void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4);
	}

	@SuppressLint(value = { "NewApi" })
	class PopMenuAdapter_01 extends BaseAdapter {
		class ViewHolder {
			TextView tv;

			private ViewHolder(PopMenuAdapter_01 arg1) {
				super();
			}
		}

		List allSelectors;

		public PopMenuAdapter_01(FilterPopupWindowTools x, List arg2) {
			super();
			this.allSelectors = arg2;
		}

		public int getCount() {
			return this.allSelectors.size();
		}

		public Object getItem(int position) {
			return this.allSelectors.get(position);
		}

		public long getItemId(int position) {
			return ((long) position);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder v0 = null;
			if (convertView == null) {
				v0 = new ViewHolder(this);
				convertView = View.inflate(FilterPopupWindowTools.this.context, R.layout.simple_textview, null);
				v0.tv = (TextView) convertView.findViewById(R.id.tv_name);
				convertView.setTag(v0);
			} else {
				v0 = (ViewHolder) convertView.getTag();
			}

			v0.tv.setText((CharSequence) this.allSelectors.get(position));
			return convertView;
		}
	}

	private List allSelectors;
	private View contentView;
	private Context context;
	private int height;
	private MyOnItemClick itemClick;
	private ListView lv_selector;
	private PopMenuAdapter_01 menuAdapter_01;
	private PopupWindow popupWindow;
	private View v;
	private int width;

	public FilterPopupWindowTools(Context context, int width, int height, List arg4) {
		super();
		this.context = context;
		this.width = width;
		this.height = height;
		this.allSelectors = arg4;
	}
	
	public void dismiss() {
		this.popupWindow.dismiss();
	}

	public MyOnItemClick getItemClick() {
		return this.itemClick;
	}

	private void getPop() {
		int v6 = -1;
		this.contentView = View.inflate(this.context, R.layout.layout_menu, null);
		View v2 = this.contentView.findViewById(R.id.menu_list);
		LinearLayout.LayoutParams v1 = (LayoutParams) (v2).getLayoutParams();
		if (v1 == null) {
			v1 = new LinearLayout.LayoutParams(v6, v6);
		}

		v1.gravity = 48;
		this.contentView.setLayoutParams(v1);
		((ListView) v2).setAdapter(new PopMenuAdapter_01(this, this.allSelectors));
		((ListView) v2).setOnItemClickListener(((AdapterView.OnItemClickListener) this));
		this.popupWindow = new PopupWindow(this.contentView, this.width, this.height, true);
		this.popupWindow.setBackgroundDrawable(new BitmapDrawable());
		this.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			public void onDismiss() {
			}
		});
	}

	private void initPopupWindow() {
		if (this.popupWindow == null || !this.popupWindow.isShowing()) {
			this.getPop();
		} else {
			this.popupWindow.dismiss();
		}
	}

	public void onItemClick(AdapterView arg7, View subView, int position, long arg3) {
		this.dismiss();
		if (this.v != null && ((this.v instanceof TextView))) {
			((TextView) this.v).setText((CharSequence) this.allSelectors.get(position));
		}

		switch (arg7.getId()) {
		case R.id.menu_list: {
			if (this.itemClick != null) {
				this.itemClick.onItemClick(arg7, subView, position, arg3);
			}

			break;
		}
		}
	}

	public void setItemClick(MyOnItemClick itemClick) {
		this.itemClick = itemClick;
	}

	public void showAsDropDown(View v) {
		this.initPopupWindow();
		this.v = v;
		this.popupWindow.showAsDropDown(v, -15, -3);
		this.popupWindow.setFocusable(true);
		this.popupWindow.setOutsideTouchable(true);
		this.popupWindow.update();
	}

	public void showAtLocation(LinearLayout parent) {
		this.initPopupWindow();
		this.popupWindow.showAtLocation(((View) parent), 53, 0, 0);
		this.popupWindow.setFocusable(true);
		this.popupWindow.setOutsideTouchable(true);
		this.popupWindow.update();
	}
}
