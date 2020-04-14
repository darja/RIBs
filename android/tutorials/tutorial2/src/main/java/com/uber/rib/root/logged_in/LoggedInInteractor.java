package com.uber.rib.root.logged_in;

import androidx.annotation.Nullable;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.EmptyPresenter;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.uber.rib.root.logged_in.off_game.OffGameInteractor;
import com.uber.rib.root.logged_in.tic_tac_toe.TicTacToeInteractor;

import javax.inject.Inject;

/**
 * Coordinates Business Logic for {@link LoggedInBuilder.LoggedInScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class LoggedInInteractor extends Interactor<EmptyPresenter, LoggedInRouter> {
  @Inject @LoggedInBuilder.LoggedInInternal MutableScoreStream scoreStream;

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);

    getRouter().attachOffGame();
  }

  @Override
  protected void willResignActive() {
    super.willResignActive();

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  class OffGameListener implements OffGameInteractor.Listener {

    @Override
    public void onStartGame() {
      getRouter().detachOffGame();
      getRouter().attachTicTacToe();
    }
  }

  class TicTacToeListener implements TicTacToeInteractor.Listener {
    @Override
    public void onGameOver(String winner) {
      scoreStream.addVictory(winner);
      getRouter().detachTicTacToe();
      getRouter().attachOffGame();
    }
  }
}
