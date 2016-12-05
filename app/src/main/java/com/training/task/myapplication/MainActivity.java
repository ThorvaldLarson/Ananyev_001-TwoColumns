package com.training.task.myapplication;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int COLUMN_MAGRIN_DP = 20;
    private final String COLUMN_BACKGROUND_COLOR = "#669801";
    private final int TEXTVIEW_COUNT = 3;
    private final int TEXTVIEW_DRAWABLE_PADDING_DP = 5;
    private final String TEXTVIEW_TEXT = "Some text";
    private final String TEXTVIEW_TEXT_COLOR = "#a5a5a5";
    private final String TEXTVIEW_BACKGROUND_COLOR = "#fdfdfe";
    private final int TEXTVIEW_MARGIN_DP = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int columnMarginInPixels = convertDpToPx(COLUMN_MAGRIN_DP);
        int columnWidthInPixels = getScreenWidth() / 2 - columnMarginInPixels * 2;

        setFirstColumnsWidth(columnWidthInPixels);

        LinearLayout column = createColumn(1, columnWidthInPixels, columnMarginInPixels);
        for (int i = 0; i < TEXTVIEW_COUNT; i++) {
            column.addView(createTextView());
        }

        GridLayout container = (GridLayout) findViewById(R.id.container);
        container.addView(column);
    }

    private int getScreenWidth() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    private int convertDpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private void setFirstColumnsWidth(int width) {
        LinearLayout firstColumn = (LinearLayout) findViewById(R.id.first_column);
        firstColumn.getLayoutParams().width = width;
    }

    private LinearLayout createColumn(int columnPosition, int widthInPixels, int marginInPixels) {
        GridLayout.LayoutParams columnParams;
        LinearLayout column;

        columnParams = new GridLayout.LayoutParams();
        columnParams.width = widthInPixels;
        columnParams.height = GridLayout.LayoutParams.MATCH_PARENT;
        columnParams.rowSpec = GridLayout.spec(0);
        columnParams.columnSpec = GridLayout.spec(columnPosition);
        columnParams.setMargins(marginInPixels, marginInPixels, marginInPixels, marginInPixels);

        column = new LinearLayout(this);
        column.setOrientation(LinearLayout.VERTICAL);
        column.setBackgroundColor(Color.parseColor(COLUMN_BACKGROUND_COLOR));
        column.setLayoutParams(columnParams);

        return column;
    }

    private TextView createTextView() {
        TextView textView;
        LinearLayout.LayoutParams textViewParams;
        int textViewMarginPixels = convertDpToPx(TEXTVIEW_MARGIN_DP);
        int textViewDrawablePaddingPixels = convertDpToPx(TEXTVIEW_DRAWABLE_PADDING_DP);

        textViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(textViewMarginPixels, textViewMarginPixels, textViewMarginPixels,
                textViewMarginPixels);

        textView = new TextView(this);
        textView.setText(TEXTVIEW_TEXT);
        textView.setTextColor(Color.parseColor(TEXTVIEW_TEXT_COLOR));
        textView.setBackgroundColor(Color.parseColor(TEXTVIEW_BACKGROUND_COLOR));
        textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher, 0, 0, 0);
        textView.setCompoundDrawablePadding(textViewDrawablePaddingPixels);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(textViewParams);

        return textView;
    }
}