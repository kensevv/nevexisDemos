package com.nevexis.mapping;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
	@Expose
	@SerializedName(value = "XXBTZUSD", alternate = { "XETHZUSD", "XDGUSD","ADAUSD","XXRPZUSD" })
	public List<List<String>> data;
}
