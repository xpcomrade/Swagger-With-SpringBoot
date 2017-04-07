package my.study.base.model;

import java.io.Serializable;

public class Page<T> implements Serializable {


	public static final int TOTAL_DEFAULT = 0;

	public static final int LENGTH_DEFAULT = 10;

	public static final int PAGENUM_DEFAULT = 1;

	/**
	 * 结果总条数
	 */
	private long total = TOTAL_DEFAULT;

	/**
	 * 查询偏移量
	 */
	private long offset = 0;

	/**
	 * 查询长度（每页显示条数）
	 */
	private long length = LENGTH_DEFAULT;

	/**
	 * 页数
	 */
	private int page_num = PAGENUM_DEFAULT;

	private T data;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getOffset() {
		this.offset = (page_num - 1) * length;
		return offset;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}


	public int getPage_num() {
		return page_num;
	}

	public void setPage_num(int page_num) {
		this.page_num = page_num;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
