package es.olidroide.notepad.notes.view;

import android.content.Context;
import android.content.Intent;
import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.RosieActivity;
import es.olidroide.notepad.R;
import es.olidroide.notepad.notes.NotesModule;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class NotesActivity extends RosieActivity {

    public static void open(Context context) {
        Intent intent = new Intent(context, NotesActivity.class);
        context.startActivity(intent);
    }

    @Override protected int getLayoutId() {
        return R.layout.activity_notes;
    }

    @Override protected List<Object> getActivityScopeModules() {
        return Collections.singletonList((Object) new NotesModule());
    }
}
