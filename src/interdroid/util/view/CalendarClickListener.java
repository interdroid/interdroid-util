package interdroid.util.view;

/**
 * Listener for clicks on calendar days in a calendar view.
 * @author nick &lt;palmer@cs.vu.nl&gt;
 *
 */
public interface CalendarClickListener {
	/**
	 * Called when the calendar is clicked on.
	 * @param day the day clicked
	 * @param month the 0 indexed month clicked
	 * @param mYear
	 */
	void onCalendarClicked(int day, int month, int year);
}
