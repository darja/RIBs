package com.uber.rib.root.logged_in;

import com.uber.rib.core.Builder;
import com.uber.rib.core.EmptyPresenter;
import com.uber.rib.core.InteractorBaseComponent;
import com.uber.rib.root.RootView;
import com.uber.rib.root.logged_in.off_game.OffGameBuilder;
import com.uber.rib.root.logged_in.off_game.OffGameInteractor;
import com.uber.rib.root.logged_in.tic_tac_toe.TicTacToeBuilder;
import com.uber.rib.root.logged_in.tic_tac_toe.TicTacToeInteractor;

import java.lang.annotation.Retention;

import javax.inject.Named;
import javax.inject.Qualifier;
import javax.inject.Scope;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Provides;

import static java.lang.annotation.RetentionPolicy.CLASS;

public class LoggedInBuilder extends Builder<LoggedInRouter, LoggedInBuilder.ParentComponent> {

  public LoggedInBuilder(ParentComponent dependency) {
    super(dependency);
  }

  /**
   * Builds a new {@link LoggedInRouter}.
   *
   * @return a new {@link LoggedInRouter}.
   */
  public LoggedInRouter build(String player1, String player2) {
    LoggedInInteractor interactor = new LoggedInInteractor();
    Component component = DaggerLoggedInBuilder_Component.builder()
        .parentComponent(getDependency())
        .interactor(interactor)
        .playerOne(player1)
        .playerTwo(player2)
        .build();

    return component.loggedinRouter();
  }

  public interface ParentComponent {
    RootView rootView();
  }

  @dagger.Module
  public abstract static class Module {

    @LoggedInScope
    @Provides
    static EmptyPresenter presenter() {
      return new EmptyPresenter();
    }

    @LoggedInScope
    @Provides
    static LoggedInRouter router(Component component, LoggedInInteractor interactor,
                                 RootView rootView) {
      return new LoggedInRouter(interactor, component, rootView,
              new OffGameBuilder(component),
              new TicTacToeBuilder(component));
    }

    @LoggedInScope
    @Provides
    static OffGameInteractor.Listener offGameListener(LoggedInInteractor interactor) {
      return interactor.new OffGameListener();
    }

    @LoggedInScope
    @Provides
    static TicTacToeInteractor.Listener ticTacToeListener(LoggedInInteractor interactor) {
      return interactor.new TicTacToeListener();
    }

    @LoggedInScope
    @LoggedInInternal
    @Provides
    static MutableScoreStream mutableScoreStream(
            @Named("player_one") String playerOne,
            @Named("player_two") String playerTwo) {
      return new MutableScoreStream(playerOne, playerTwo);
    }

    @LoggedInScope
    @Binds
    abstract ScoreStream scoreStream(@LoggedInInternal MutableScoreStream mutableScoreStream);
  }

  @LoggedInScope
  @dagger.Component(modules = Module.class, dependencies = ParentComponent.class)
  public interface Component extends InteractorBaseComponent<LoggedInInteractor>,
          OffGameBuilder.ParentComponent,
          TicTacToeBuilder.ParentComponent,
          BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(LoggedInInteractor interactor);
      Builder parentComponent(ParentComponent component);
      Component build();

      @BindsInstance
      Builder playerOne(@Named("player_one") String playerOne);

      @BindsInstance
      Builder playerTwo(@Named("player_two") String playerTwo);
    }

  }

  interface BuilderComponent {
    LoggedInRouter loggedinRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface LoggedInScope { }


  @Qualifier
  @Retention(CLASS)
  @interface LoggedInInternal { }
}
