package com.mkpiutang.webservice.dto;

import java.util.List;

public class PagingDto<T> {
	
	public static int DEFAULT_RESULTS_PER_PAGE = 200;

	private List<T> records;
	private List<T> paging;
	private Integer resultPerPage;
	private Integer currentPage;
	private Integer resultSize;
	
	public PagingDto() {
		this(DEFAULT_RESULTS_PER_PAGE, 1);
	}

	public List<T> getPaging() {
		return paging;
	}

	public void setPaging(List<T> paging) {
		this.paging = paging;
	}

	public PagingDto(Integer resultsPerPage, Integer currentPage) {
		this.resultPerPage = resultsPerPage;
		this.currentPage = currentPage;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public Integer getResultPerPage() {
		return resultPerPage;
	}

	public void setResultPerPage(Integer resultPerPage) {
		this.resultPerPage = resultPerPage;
	}

	public Integer getCurrentPage() {
		if (currentPage == null)
			return 1;
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getResultSize() {
		return resultSize;
	}

	public void setResultSize(Integer resultSize) {
		this.resultSize = resultSize;
	}
	
	
}
