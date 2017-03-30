package com.ryan.slidefragment.model;

import java.io.Serializable;

public class Person implements Serializable {
	private Object name;

	public Object getName() {
		return name;
	}

	public void setName(Object object) {
		this.name = object;
	}
}
