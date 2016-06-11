package com.joke.bamenshenqi.module.ui.view.common;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class CommonAdapter extends BaseAdapter {
    protected Context mContext;
    protected List mDatas;
    protected LayoutInflater mInflater;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, List arg3, int itemLayoutId) {
        super();
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mDatas = arg3;
        this.mItemLayoutId = itemLayoutId;
    }

    public abstract void convert(ViewHolder arg1, Object arg2);

    public int getCount() {
        int v0 = this.mDatas == null ? 0 : this.mDatas.size();
        return v0;
    }

    public List getData() {
        return this.mDatas;
    }

    public Object getItem(int position) {
        return this.mDatas.get(position);
    }

    public long getItemId(int position) {
        return ((long)position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v0 = this.getViewHolder(position, convertView, parent);
        this.convert(v0, this.getItem(position));
        return v0.getConvertView();
    }

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(this.mContext, convertView, parent, this.mItemLayoutId, position);
    }

    public void setData(List arg1) {
        this.mDatas = arg1;
    }
}

