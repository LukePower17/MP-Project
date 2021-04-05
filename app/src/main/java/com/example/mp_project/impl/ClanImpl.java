package com.example.mp_project.impl;

import com.example.mp_project.BTClashWrapper;
import com.example.mp_project.Clan;
import com.example.mp_project.ClanMember;
import com.example.mp_project.ClashAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClanImpl extends AbbreviatedClanImpl implements Clan {

    private String description;
    private List<ClanMember> members = new ArrayList<ClanMember>();

    public ClanImpl(JSONObject root) throws JSONException {
        super(root);
        description = root.getString("description");
        JSONArray memberList = root.getJSONArray("memberList");
        for (int i = 0; i < memberList.length(); i++) {
            members.add(new ClanMemberImpl(memberList.getJSONObject(i)));
        }
    }

    public String getDescription() {
        return description;
    }

    public List<ClanMember> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "ClanImpl{" +
                "description='" + description + '\'' +
                ", members=" + members +
                "} " + super.toString();
    }
}
