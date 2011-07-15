package interdroid.util;


import android.app.Activity;
import android.widget.Toast;

public final class ToastOnUI {

	public static void show(final Activity context, final int messageId, final int length) {
		context.runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(context, messageId, length).show();
			}
		});
	}

	public static void show(final Activity context, final String message, final int length) {
		context.runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(context, message, length).show();
			}
		});
	}
}
