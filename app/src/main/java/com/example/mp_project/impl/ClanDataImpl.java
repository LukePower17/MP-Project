package com.example.mp_project.impl;

import com.example.mp_project.ClanData;
import com.example.mp_project.IconSet;

import org.json.JSONException;
import org.json.JSONObject;

public class ClanDataImpl implements ClanData {

    protected String tag;
    protected String name;
    protected IconSet badgeIcons;

    protected ClanDataImpl(JSONObject root) throws JSONException {
        tag = root.getString("tag");
        name = root.getString("name");
        badgeIcons = new IconSetImpl(root.getJSONObject("badgeUrls"));
    }

    public IconSet getBadgeIcons() {
        return badgeIcons;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "ClanDataImpl{" +
                "tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", badgeIcons=" + badgeIcons +
                '}';
    }
}
