package com.uber.rib.root.logged_in;

import com.google.common.collect.ImmutableMap;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.Map;

import io.reactivex.Observable;

class MutableScoreStream implements ScoreStream {
    private final BehaviorRelay<ImmutableMap<String, Integer>> scoresRelay = BehaviorRelay.create();

    MutableScoreStream(String playerOne, String playerTwo) {
        scoresRelay.accept(ImmutableMap.of(playerOne, 0, playerTwo, 0));
    }

    void addVictory(String userName) {
        ImmutableMap<String, Integer> currentScores = scoresRelay.getValue();

        ImmutableMap.Builder<String, Integer> newScoreMapBuilder = new ImmutableMap.Builder<>();
        for (Map.Entry<String, Integer> entry : currentScores.entrySet()) {
            if (entry.getKey().equals(userName)) {
                newScoreMapBuilder.put(entry.getKey(), entry.getValue() + 1);
            } else {
                newScoreMapBuilder.put(entry.getKey(), entry.getValue());
            }
        }

        scoresRelay.accept(newScoreMapBuilder.build());
    }

    @Override
    public Observable<ImmutableMap<String, Integer>> scores() {
        return scoresRelay.hide();
    }
}
