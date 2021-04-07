package com.example.mp_project.cocmodels.clanwar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attack
{
	@SerializedName("order")
	@Expose
	private Integer order;
	
	@SerializedName("attackerTag")
	@Expose
	private String attackerTag;
	
	@SerializedName("defenderTag")
	@Expose
	private String defenderTag;
	
	@SerializedName("stars")
	@Expose
	private Integer stars;
	
	@SerializedName("destructionPercentage")
	@Expose
	private Double destructionPercentage;

	public Integer getOrder()
	{
		return order;
	}

	public String getAttackerTag()
	{
		return attackerTag;
	}

	public String getDefenderTag()
	{
		return defenderTag;
	}

	public Integer getStars()
	{
		return stars;
	}

	public Double getDestructionPercentage()
	{
		return destructionPercentage;
	}
}
