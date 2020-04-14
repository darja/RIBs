package com.uber.rib.loggedout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
public class LoggedOutView extends View implements LoggedOutInteractor.LoggedOutPresenter {

  public LoggedOutView(Context context) {
    this(context, null);
  }

  public LoggedOutView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LoggedOutView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }
}
