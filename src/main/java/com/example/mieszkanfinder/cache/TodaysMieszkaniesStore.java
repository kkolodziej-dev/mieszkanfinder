package com.example.mieszkanfinder.cache;

import com.example.mieszkanfinder.datamodels.GenericMieszkanieModel;
import com.example.mieszkanfinder.helpers.GlobalCredentialsStore;
import com.example.mieszkanfinder.logic.GetMieszkaniesLogicManipulator;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.mieszkanfinder.helpers.GlobalCredentialsStore.TIME_NOW;

/*
* This class is for detecting new mieszkanies.
* If there are new mieszkanies detected we want to send them in the newsletter.
* If the post newsletter was triggered but no new mieszkanies were found don't send the email.
* */
public class TodaysMieszkaniesStore {
    // This map should always have one member
    public static Map<String, List<GenericMieszkanieModel>> store = new HashMap<>();

    public static List<GenericMieszkanieModel> getTodaysMieszkaniesDelta() {
        GlobalCredentialsStore.refreshTimeNow();

        GetMieszkaniesLogicManipulator manipulator = new GetMieszkaniesLogicManipulator();
        List<GenericMieszkanieModel> todaysMieszkanies = manipulator.getMieszkanies();
        todaysMieszkanies.add(new GenericMieszkanieModel());

        // First time cache is initialized it's empty, add today's date + list of mieszkanies
        if (store.isEmpty()) {
            store.put(TIME_NOW, todaysMieszkanies);
            return store.get(TIME_NOW);
        }

        // If the cache contains data from previous day reset it and fill with today's data
        if (store.get(TIME_NOW).isEmpty()) {
            store = new HashMap<>();
            store.put(TIME_NOW, todaysMieszkanies);
        }

        // Now something is cached but meanwhile more mieszkanies could have appeared
        // Find new mieszkanies and return as delta if any
        var storedList = store.get(TIME_NOW);
        List<GenericMieszkanieModel> delta = todaysMieszkanies.stream().filter(x -> !storedList.contains(x)).collect(Collectors.toList());
        storedList.addAll(delta);

        return delta;
    }
}