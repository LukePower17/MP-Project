package com.example.mp_project.cocmodels.clanwar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarlogItem
{
	@SerializedName("result")
	@Expose
	private String result;
	
	@SerializedName("endTime")
	@Expose
	private String endTime;
	
	@SerializedName("teamSize")
	@Expose
	private Integer teamSize;
	
	@SerializedName("clan")
	@Expose
	private ClanWarModel clan;
	
	@SerializedName("opponent")
	@Expose
	private ClanWarModel opponent;

	public String getResult()
	{
		return result;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public Integer getTeamSize()
	{
		return teamSize;
	}

	public ClanWarModel getClan()
	{
		return clan;
	}

	public ClanWarModel getOpponent()
	{
		return opponent;
	}
}
