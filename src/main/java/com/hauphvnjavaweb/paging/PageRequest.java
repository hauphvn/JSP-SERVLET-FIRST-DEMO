package com.hauphvnjavaweb.paging;

import com.hauphvnjavaweb.sort.Sorter;

public class PageRequest implements Pageble {
	
	private Integer page;
	private Integer maxItem;
	private Sorter sorter;
	
	public PageRequest(Integer page, Integer maxItem, Sorter sorter) {
		this.page = page;
		this.maxItem = maxItem;
		this.setSorter(sorter);
	}

	@Override
	public Integer getPage() {
		return page;
	}

	@Override
	public Integer getOffset() {
		if(page != null && maxItem != null) {
			return ((this.page - 1) * this.maxItem);
		}
		return null;
	}

	@Override
	public Integer getLimit() {
		return maxItem;
	}

	public Sorter getSorter() {
		if(this.sorter != null) {
			return this.sorter;
		}
		return null;
	}

	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}

}
