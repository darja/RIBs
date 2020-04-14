package com.uber.rib.root.logged_out;

import com.uber.rib.core.InteractorHelper;
import com.uber.rib.core.RibTestBasePlaceholder;
import com.uber.rib.model.GameStart;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("NullAway")
public class LoggedOutInteractorTest extends RibTestBasePlaceholder {

    @Mock LoggedOutInteractor.LoggedOutPresenter presenter;
    @Mock LoggedOutRouter router;
    @Mock LoggedOutInteractor.Listener listener;

    private LoggedOutInteractor interactor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        interactor = TestLoggedOutInteractor.create(presenter, listener);
    }

    @Test
    public void attach_whenViewEmitsName_shouldCallListener() {
        when(presenter.gameStart()).thenReturn(Observable.just(new GameStart("fake1", "fake2")));

        InteractorHelper.attach(interactor, presenter, router, null);
        verify(listener).startGame(any(GameStart.class));
    }

    @Test
    public void attach_whenViewEmitsEmptyPlayer1Name_shouldNotCallListener() {
        when(presenter.gameStart()).thenReturn(Observable.just(new GameStart("", "fake2")));

        InteractorHelper.attach(interactor, presenter, router, null);
        // This test will fail because the interactor doesn’t have any logic for handling empty strings.
        // You’ll need to fix this as discussed above the code snippet.
        verify(listener, never()).startGame(any(GameStart.class));
    }

    @Test
    public void attach_whenViewEmitsEmptyPlayer2Name_shouldNotCallListener() {
        when(presenter.gameStart()).thenReturn(Observable.just(new GameStart("fake1", "")));

        InteractorHelper.attach(interactor, presenter, router, null);
        // This test will fail because the interactor doesn’t have any logic for handling empty strings.
        // You’ll need to fix this as discussed above the code snippet.
        verify(listener, never()).startGame(any(GameStart.class));
    }
}
