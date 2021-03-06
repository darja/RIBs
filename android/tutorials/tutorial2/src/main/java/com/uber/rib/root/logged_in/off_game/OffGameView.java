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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.core.Initializer;
import com.uber.rib.tutorial1.R;

import io.reactivex.Observable;

/**
 * Top level view for {@link OffGameBuilder.OffGameScope}.
 */
public class OffGameView extends LinearLayout implements OffGameInteractor.OffGamePresenter {

  private Button button;
  private TextView player1View;
  private TextView player2View;
  private TextView score1View;
  private TextView score2View;

  public OffGameView(Context context) {
    this(context, null);
  }

  public OffGameView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public OffGameView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Initializer
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    button = findViewById(R.id.start_game_button);

    player1View = findViewById(R.id.player_one_name);
    player2View = findViewById(R.id.player_two_name);

    score1View = findViewById(R.id.player_one_win_count);
    score2View = findViewById(R.id.player_two_win_count);
  }

  @Override
  public void setPlayerNames(String player1, String player2) {
    player1View.setText(player1);
    player2View.setText(player2);
  }

  @Override
  public void setScores(@Nullable Integer score1, @Nullable Integer score2) {
    score1View.setText(score1 == null ? "0" : score1.toString());
    score2View.setText(score2 == null ? "0" : score2.toString());
  }

  @Override
  public Observable<Object> startGameRequest() {
    return RxView.clicks(button);
  }
}
