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

/**
 * Create an instance of this class to start using the API.<br>
 * 
 * Lost? Check the <a href="https://github.com/Lycoon/clash-api">README</a> to see what ClashAPI is all about.
 */
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
	
	/**
	 * Returns the player attached to the tag.<br><br>
	 * The tag is a unique identifier each player has, in the form of #AAAA00.<br>
	 * It is displayed under the nickname on player's profile.
	 * 
	 * @param playerTag - <code>String</code> of the player's tag
	 * @return a <code>Player</code> object
	 * @see Player
	 * @throws IOException if the deserialization failed
	 * @throws ClashAPIException if the request to the game API failed
	 */
	public Player getPlayer(String playerTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("players/" +formatTag(playerTag));
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), Player.class);
	}
	
	/**
	 * Returns the clan attached to the tag.<br><br>
	 * The tag is a unique identifier each clan has, in the form of #AAAA00.<br>
	 * It is displayed under the clan name on clan's profile.
	 * 
	 * @param clanTag - <code>String</code> of the clan's tag
	 * @return a <code>ClanModel</code> object
	 * @see ClanModel
	 * @throws IOException if the deserialization failed
	 * @throws ClashAPIException if the request to the game API failed
	 */
	public ClanModel getClan(String clanTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("clans/" +formatTag(clanTag));
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), ClanModel.class);
	}
	
	/**
	 * Returns the clan war occurring in the clan with the given tag.<br><br>
	 * The tag is a unique identifier each clan has, in the form of #AAAA00.<br>
	 * It is displayed under the clan name on clan's profile.
	 * 
	 * @param clanTag - <code>String</code> of the clan's tag
	 * @return a <code>WarInfo</code> object
	 * @see WarInfo
	 * @throws IOException if the deserialization failed
	 * @throws ClashAPIException if the request to the game API failed
	 */
	public WarInfo getCurrentWar(String clanTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("clans/" +formatTag(clanTag)+ "/currentwar");
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), WarInfo.class);
	}
	
	/**
	 * Returns the warlog of the clan with the given tag.<br><br>
	 * The tag is a unique identifier each clan has, in the form of #AAAA00.<br>
	 * It is displayed under the clan name on clan's profile.
	 * 
	 * @param clanTag - <code>String</code> of the clan's tag
	 * @return a <code>WarlogModel</code> object
	 * @see WarlogModel
	 * @throws IOException if the deserialization failed
	 * @throws ClashAPIException if the request to the game API failed
	 */
	public WarlogModel getWarlog(String clanTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("clans/" +formatTag(clanTag)+ "/warlog");
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), WarlogModel.class);
	}
	
	/**
	 * Returns the CWL group in which the clan with the given tag is.<br><br>
	 * The tag is a unique identifier each clan has, in the form of #AAAA00.<br>
	 * It is displayed under the clan name on clan's profile.
	 * 
	 * @param clanTag - <code>String</code> of the clan's tag
	 * @return a <code>WarLeagueGroup</code> object
	 * @see WarLeagueGroup
	 * @throws IOException if the deserialization failed
	 * @throws ClashAPIException if the request to the game API failed
	 */
	public WarLeagueGroup getCWLGroup(String clanTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("clans/" +formatTag(clanTag)+ "/currentwar/leaguegroup");
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), WarLeagueGroup.class);
	}
	
	/**
	 * Returns the individual CWL war associated to the given war tag.<br><br>
	 * You can obtain individual CWL war tags from:<br>
	 * <code>ClashAPI.getCWLGroup(clanTag).getRounds(index).getWarTags(index)</code>
	 * 
	 * @param warTag - <code>String</code> of the war tag
	 * @return a <code>WarInfo</code> object
	 * @see WarInfo
	 * @throws IOException if the deserialization failed
	 * @throws ClashAPIException if the request to the game API failed
	 */
	public WarInfo getCWLWar(String warTag) throws IOException, ClashAPIException
	{
		Response res = makeAPICall("clanwarleagues/wars/" +formatTag(warTag));
		return gson.fromJson(Objects.requireNonNull(res.body()).string(), WarInfo.class);
	}
}
