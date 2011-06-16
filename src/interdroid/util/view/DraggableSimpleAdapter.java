package interdroid.util.view;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.SimpleAdapter;

public class DraggableSimpleAdapter extends SimpleAdapter implements DraggableAdapter {

	// Why the hell isn't this protected in SimpleAdapter or there are remove and add methods there?
	private List<Map<String, Object>> mData;

	/**
     * The resource specified must include a view with IDs interdroid.util.R.drag_handle and interdroid.util.R.remove_button.
     */
	public DraggableSimpleAdapter(Context context, List<Map<String, Object>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		mData = data;
	}

	@Override
	public void onRemove(int offset) {
		mData.remove(offset);
		notifyDataSetChanged();
	}

	@Override
	public void onDrop(int from, int to) {
		mData.add(from < to ? to - 1 : to, mData.remove(from));
		notifyDataSetChanged();
	}

}
