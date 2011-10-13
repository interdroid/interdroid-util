package interdroid.util.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Various static utility methods for working with views.
 *
 * @author nick &lt;palmer@cs.vu.nl&gt;
 *
 */
public class ViewUtil {
	/**
	 * Returns the layout inflater for a given activity.
	 * @param context The context to get the inflater from.
	 * @return The layout inflater used by this activity
	 */
	public static LayoutInflater getLayoutInflater(final Context context) {
		return (LayoutInflater) context.getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * Adds a view on the UI thread of the given activity.
	 * @param activity The activity to run on the UI thread of
	 * @param viewGroup The view group to add to
	 * @param view The view to be added
	 */
	public static void addView(final Activity activity,
			final ViewGroup viewGroup, final View view) {
		if (viewGroup != null) {
			activity.runOnUiThread(new Runnable() {
				public void run() {
					viewGroup.addView(view);
				}
			});
		}
	}

}
