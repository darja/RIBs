package com.uber.rib.root.logged_out;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.model.GameStart;
import com.uber.rib.tutorial1.R;

import io.reactivex.Observable;

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
public class LoggedOutView extends LinearLayout implements LoggedOutInteractor.LoggedOutPresenter {
    private TextView player1View;
    private TextView player2View;

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

        player1View = findViewById(R.id.player1);
        player2View = findViewById(R.id.player2);
    }

    @Override
    public Observable<GameStart> gameStart() {
        return RxView.clicks(findViewById(R.id.login_button))
            .map(o -> new GameStart(getPlayer1(), getPlayer2()));
    }

    private String getPlayer1() {
        return player1View.getText().toString();
    }

    private String getPlayer2() {
        return player2View.getText().toString();
    }
}
