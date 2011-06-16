package interdroid.util.view;

import interdroid.util.R;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DraggableArrayAdapter extends ArrayAdapter<String> implements DraggableAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(DraggableArrayAdapter.class);

    protected boolean mRemoveable = true;

    public DraggableArrayAdapter(Context context, List<String> content) {
        super(context, R.layout.draggable_item, R.id.drag_label, content);
    }

    /**
     * The layout specified must include a view with IDs interdroid.util.R.drag_handle and interdroid.util.R.remove_button.
     */
    public DraggableArrayAdapter(Context context, int layout, int itemID, List<String> content) {
    	super(context, layout, itemID, content);
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = super.getView(position, convertView, parent);

	    if (!mRemoveable) {
	    	convertView.findViewById(R.id.remove_button).setVisibility(View.GONE);
	    }

	    return convertView;
	}

    public void setRemoveable(boolean b) {
    	mRemoveable = b;
    }

	public void onRemove(int which) {
		logger.debug("onRemove: {}", which);
		if (mRemoveable && which >= 0 && which < getCount()) {
			remove(getItem(which));
			notifyDataSetChanged();
		}
	}

	public void onDrop(int from, int to) {
		logger.debug("onDrop: {} {}", from, to);
		String item = getItem(from);
		remove(item);
		insert(item, from < to ? to - 1 : to);
		notifyDataSetChanged();
	}

}
