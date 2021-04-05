package com.example.mp_project.impl;

import com.example.mp_project.League;
import com.example.mp_project.Player;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class PlayerImpl implements Player {

    protected String name;
    protected int expLevel;
    protected int trophies;
    protected League league;

    protected PlayerImpl(JSONObject root) throws JSONException {
        name = root.getString("name");
        expLevel = root.getInt("expLevel");
        if (root.has("league")) {
            league = new LeagueImpl(root.getJSONObject("league"));
        }
        trophies = root.getInt("trophies");
    }

    public int getExpLevel() {
        return expLevel;
    }

    public League getLeague() {
        return league;
    }

    public String getName() {
        return name;
    }

    public int getTrophies() {
        return trophies;
    }

    @Override
    public String toString() {
        return "PlayerImpl{" +
                "name='" + name + '\'' +
                ", expLevel=" + expLevel +
                ", trophies=" + trophies +
                ", league=" + league +
                '}';
    }
}
