package com.mkpiutang.webservice.dto;

import java.util.List;

public class RecordDto <T> {
	private List<T> records;

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
}
