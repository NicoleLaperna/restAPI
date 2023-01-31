package com.enway.entity;

public class Translate {

	public String q;

	public String target;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Translate(String q, String target) {
		super();
		this.q = q;
		this.target = target;
	}

	@Override
	public String toString() {
		return "Translate [q=" + q + ", target=" + target + "]";
	}
	
}
