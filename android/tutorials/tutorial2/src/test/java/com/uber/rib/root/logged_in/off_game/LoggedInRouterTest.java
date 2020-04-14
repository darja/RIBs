package com.uber.rib.root.logged_in.off_game;

import com.uber.rib.core.RibTestBasePlaceholder;
import com.uber.rib.core.RouterHelper;
import com.uber.rib.root.logged_in.LoggedInBuilder;
import com.uber.rib.root.logged_in.LoggedInInteractor;
import com.uber.rib.root.logged_in.LoggedInRouter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LoggedInRouterTest extends RibTestBasePlaceholder {

  @Mock LoggedInBuilder.Component component;
  @Mock
  LoggedInInteractor interactor;

  private LoggedInRouter router;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    router = new LoggedInRouter(interactor, component, offGameRouter);
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  public void anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router);
    RouterHelper.detach(router);

    throw new RuntimeException("Remove this test and add real tests.");
  }

}
