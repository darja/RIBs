/*
 * Copyright (C) 2017. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uber.rib.root.logged_in.off_game;

import android.annotation.SuppressLint;

import androidx.annotation.Nullable;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.uber.rib.root.logged_in.ScoreStream;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

/**
 * Coordinates Business Logic for {@link OffGameBuilder.OffGameScope}.
 */
@RibInteractor
public class OffGameInteractor
    extends Interactor<OffGameInteractor.OffGamePresenter, OffGameRouter> {

  @Inject Listener listener;
  @Inject OffGamePresenter presenter;
  @Inject @Named("player_one") String player1;
  @Inject @Named("player_two") String player2;
  @Inject ScoreStream scoreStream;

  @SuppressLint("CheckResult")
  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);

    presenter.setPlayerNames(player1, player2);

    scoreStream.scores()
            .subscribe(scores -> {
              if (player1 != null && player2 != null) {
                presenter.setScores(scores.get(player1), scores.get(player2));
              }
            });

    presenter
        .startGameRequest()
        .subscribe(object -> listener.onStartGame());
  }

  public interface Listener {

    void onStartGame();
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface OffGamePresenter {
    void setPlayerNames(String player1, String player2);
    void setScores(@Nullable Integer score1, @Nullable Integer score2);
    Observable<Object> startGameRequest();
  }
}
