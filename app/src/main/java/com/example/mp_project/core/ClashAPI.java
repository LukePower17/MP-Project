package com.example.mp_project.core;

import com.google.gson.Gson;
import com.example.mp_project.cocmodels.clan.ClanModel;
import com.example.mp_project.cocmodels.clanwar.WarInfo;
import com.example.mp_project.cocmodels.clanwar.WarlogModel;
import com.example.mp_project.cocmodels.clanwar.league.WarLeagueGroup;
import com.example.mp_project.cocmodels.player.Player;
import com.example.mp_project.core.exception.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;


public class ClashAPI
{
	private final static String URL = "https://api.clashofclans.com/";
	private final static String API_VERSION = "v1";
	
	private final String token;
	private final OkHttpClient http;
	private final Gson gson;
	
	public ClashAPI(String token)
	{
		this.token = token;
		http = new OkHttpClient();
		gson = new Gson();
	}
	
	private Request buildReq(String suffix)
	{
		return new Request.Builder()
			.header("authorization", "Bearer " + token)
			.url(URL + API_VERSION + "/" + suffix)
			.build();
	}
	
	private Response makeAPICall(String url) throws IOException, ClashAPIException
	{
		Response res = http.newCall(buildReq(url)).execute();
		if (!res.isSuccessful())
		{
			switch(res.code())
			{
				case 400:
					throw new BadRequestException("400");
				case 403:
					throw new AuthException("403");
				case 404:
					throw new NotFoundException("404");
				case 429:
					throw new RateLimitException("429");
				case 503:
					throw new MaintenanceException("503");
				default:
					throw new UnknownException("500");
			}
		}
		return res;
	}
	
	private String formatTag(String tag)
	{
		return tag.replace("#", "%23");
	}
	

	public Player getPlayer(String playerTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("players/" +formatTag(playerTag));
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), Player.class);
	}

	public ClanModel getClan(String clanTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("clans/" +formatTag(clanTag));
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), ClanModel.class);
	}

	public WarInfo getCurrentWar(String clanTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("clans/" +formatTag(clanTag)+ "/currentwar");
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), WarInfo.class);
	}

	public WarlogModel getWarlog(String clanTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("clans/" +formatTag(clanTag)+ "/warlog");
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), WarlogModel.class);
	}

	public WarLeagueGroup getCWLGroup(String clanTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("clans/" +formatTag(clanTag)+ "/currentwar/leaguegroup");
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), WarLeagueGroup.class);
	}

	public WarInfo getCWLWar(String warTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("clanwarleagues/wars/" +formatTag(warTag));
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), WarInfo.class);
	}
}
