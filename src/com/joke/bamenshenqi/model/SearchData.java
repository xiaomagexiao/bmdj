package com.joke.bamenshenqi.model;

import java.io.Serializable;
import java.util.List;

import com.joke.bamenshenqi.component.entity.ReportInfo;

public class SearchData implements Serializable {
	private List<ReportInfo> data;
	private int size;

	public SearchData() {
		super();
	}

	public List getData() {
		return this.data;
	}

	public int getSize() {
		return this.size;
	}

	public void setData(List arg1) {
		this.data = arg1;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String toString() {
		return "SearchData{size=" + this.size + ", data=" + this.data + '}';
	}
}
