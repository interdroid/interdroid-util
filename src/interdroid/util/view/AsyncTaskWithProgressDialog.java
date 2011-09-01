package interdroid.util.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public abstract class AsyncTaskWithProgressDialog<A, B, C> extends AsyncTask<A, B, C>{
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
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
			mDialog = null;
		}
	}
}
