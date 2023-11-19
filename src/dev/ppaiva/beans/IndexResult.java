package dev.ppaiva.beans;

public class IndexResult {
	private int index;
	private boolean isResult;

	public IndexResult(int index, boolean isResult) {
		this.index = index;
		this.isResult = isResult;
	}

	public int getIndex() {
		return index;
	}

	public boolean isResult() {
		return isResult;
	}
}
