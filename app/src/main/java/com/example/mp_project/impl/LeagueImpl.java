package com.example.mp_project.impl;

import com.example.mp_project.IconSet;
import com.example.mp_project.League;

import org.json.JSONException;
import org.json.JSONObject;

public class LeagueImpl implements League {

    private int id;
    private String name;
    private IconSet icons;

    protected LeagueImpl(JSONObject root) throws JSONException {
        id = root.getInt("id");
        name = root.getString("name");
        icons = new IconSetImpl(root.getJSONObject("iconUrls"));
    }

    public IconSet getIcons() {
        return icons;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "LeagueImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icons=" + icons +
                '}';
    }
}
