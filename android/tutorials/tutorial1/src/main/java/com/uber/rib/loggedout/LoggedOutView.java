package com.uber.rib.loggedout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.tutorial1.R;

import io.reactivex.Observable;

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
public class LoggedOutView extends LinearLayout implements LoggedOutInteractor.LoggedOutPresenter {

  private EditText loginView;
  private Button loginButton;

  public LoggedOutView(Context context) {
    this(context, null);
  }

  public LoggedOutView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LoggedOutView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();

    loginView = findViewById(R.id.edit_text);
    loginButton = findViewById(R.id.login_button);
  }

  @Override
  public Observable<String> loginName() {
    return RxView.clicks(loginButton).map((view) -> loginView.getText().toString());
  }
}
