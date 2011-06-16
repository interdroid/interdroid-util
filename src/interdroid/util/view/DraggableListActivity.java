package interdroid.util.view;

import interdroid.util.R;

import android.app.ListActivity;
import android.os.Bundle;

public class DraggableListActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.draggable_list_view);
    }
}