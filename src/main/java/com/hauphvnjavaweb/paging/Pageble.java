package com.hauphvnjavaweb.paging;

import com.hauphvnjavaweb.sort.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
	
}
