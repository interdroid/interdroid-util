package interdroid.util.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public abstract class AsyncTaskWithProgressDialog<A, B, C> extends AsyncTask<A, B, C>{
	private static final Logger logger = LoggerFactory
			.getLogger(AsyncTaskWithProgressDialog.class);

	protected Activity mContext;

	private ProgressDialog mDialog;
	private String mTitle;
	private String mMessage;

	public AsyncTaskWithProgressDialog(Activity context, String title, String message) {
		mContext = context;
		mTitle = title;
		mMessage = message;
	}

	@Override
	protected void onPreExecute() {
		mDialog = ProgressDialog.show(mContext, mTitle, mMessage, true, false);
	}

	@Override
	protected void onPostExecute(C v) {
		try {
			if (mDialog != null && mDialog.isShowing()) {
				mDialog.dismiss();
				mDialog = null;
			}
		} catch (Throwable e) {
			logger.error("Exception while closing dialog.", e);
		}
	}
}
