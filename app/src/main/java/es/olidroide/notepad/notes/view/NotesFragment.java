package es.olidroide.notepad.notes.view;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.RosieFragment;
import es.olidroide.notepad.R;
import es.olidroide.notepad.notes.viewmodel.NoteViewModel;
import java.util.List;
import javax.inject.Inject;

public class NotesFragment extends RosieFragment implements NotesFragmentPresenter.View {

    @Bind(R.id.fragment_notes_text) TextView notesTextView;
    @Bind(R.id.fragment_notes_progress) ProgressBar progressBar;

    @Inject
    @Presenter
    NotesFragmentPresenter notesFragmentPresenter;

    @Override protected int getLayoutId() {
        return R.layout.fragment_notes;
    }

    @Override protected void onPreparePresenter() {
        super.onPreparePresenter();
    }

    @Override public void updateNotes(List<NoteViewModel> notes) {
        notesTextView.setText("Total de: " + notes.size());
    }

    @Override public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
