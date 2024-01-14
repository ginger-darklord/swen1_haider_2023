package org.example.application.service;

import org.example.application.models.User;

import java.util.ArrayList;
import java.util.HashMap;

public class BattleQueuer {
    public static final BattleQueuer instance = new BattleQueuer();
    private ArrayList<User> list = new ArrayList<>();
    private HashMap<String, Battle> results = new HashMap<>();
    private Object lock = new Object();

    private BattleQueuer() {
    }

    public ArrayList<User> getList() {
        return list;
    }

    public HashMap<String, Battle> getResults() {
        return results;
    }

    public Object getLock() {
        return lock;
    }
}
