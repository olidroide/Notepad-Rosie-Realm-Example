package es.olidroide.notepad.main.view;

import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.RosieActivity;
import es.olidroide.notepad.R;
import es.olidroide.notepad.main.NotepadModule;
import es.olidroide.notepad.notes.view.NotesActivity;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class NotepadActivity extends RosieActivity implements NotepadActivityPresenter.View {

    @Inject
    @Presenter
    NotepadActivityPresenter notepadActivityPresenter;

    @Override protected List<Object> getActivityScopeModules() {
        return Collections.singletonList((Object) new NotepadModule());
    }

    @Override protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override protected void onResume() {
        super.onResume();
    }

    @Override public void hideLoading() {

    }

    @Override public void showLoading() {

    }

    @Override public void openNotesActivity() {
        NotesActivity.open(this);
        finish();
    }
}
