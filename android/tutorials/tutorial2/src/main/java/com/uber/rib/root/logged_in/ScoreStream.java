package com.uber.rib.root.logged_in;

import com.google.common.collect.ImmutableMap;

import io.reactivex.Observable;

public interface ScoreStream {
    Observable<ImmutableMap<String, Integer>> scores();
}
