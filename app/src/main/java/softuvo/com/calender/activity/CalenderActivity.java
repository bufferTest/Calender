package softuvo.com.calender.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Calendar;

import softuvo.com.calender.R;
import softuvo.com.calender.adapter.CustomDayAdapter;
import softuvo.com.calender.library.MultiCalendarView;
import softuvo.com.calender.library.OnDayClickListener;

public class CalenderActivity extends AppCompatActivity implements OnDayClickListener {

    private MultiCalendarView mMultiMonth;

    private TextView mSelectedTextView;
    private Typeface mSelectedTypeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);

        // Retrieve the CalendarView
        mMultiMonth = (MultiCalendarView) findViewById(R.id.multi_calendar);

        // Set the first valid day
        final Calendar firstValidDay = Calendar.getInstance();
        firstValidDay.set(Calendar.DAY_OF_MONTH, 1);
        mMultiMonth.setFirstValidDay(firstValidDay);

        // Set the last valid day
        final Calendar lastValidDay = Calendar.getInstance();
        lastValidDay.add(Calendar.MONTH, 12 * 3); // 3 years
        mMultiMonth.setLastValidDay(lastValidDay);

        // Create adapter
        final CustomDayAdapter adapter = new CustomDayAdapter();

        // Set listener and adapter
        mMultiMonth.setOnDayClickListener(this);
        mMultiMonth.setDayAdapter(adapter);
    }

    @Override
    public void onDayClick(final long dayInMillis) {
        // Reset the previously selected TextView to his previous Typeface
        if(mSelectedTextView != null) {
            mSelectedTextView.setTypeface(mSelectedTypeface);
        }

        final TextView day = mMultiMonth.getTextViewForDate(dayInMillis);
        if(day != null) {
            // Remember the selected TextView and it's font
            mSelectedTypeface = day.getTypeface();
            mSelectedTextView = day;

            // Show the selected TextView as bold
            day.setTypeface(Typeface.DEFAULT_BOLD);
        }
    }
}
