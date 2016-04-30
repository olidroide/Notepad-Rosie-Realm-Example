package es.olidroide.notepad.notes.view.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.karumi.rosie.renderer.RosieRenderer;
import es.olidroide.notepad.R;
import es.olidroide.notepad.notes.view.NotesFragmentPresenter;
import es.olidroide.notepad.notes.viewmodel.NoteViewModel;

public class NoteRenderer extends RosieRenderer<NoteViewModel> {

    private final NotesFragmentPresenter notesFragmentPresenter;

    @Bind(R.id.row_note_text) TextView noteText;

    public NoteRenderer(NotesFragmentPresenter notesFragmentPresenter) {
        this.notesFragmentPresenter = notesFragmentPresenter;
    }

    @Override public void render() {
        super.render();

        final NoteViewModel noteViewModel = getContent();

        noteText.setText(noteViewModel.getNote());
    }

    @OnClick(R.id.row_note_root) public void onNoteClick() {
        final NoteViewModel noteViewModel = getContent();
        notesFragmentPresenter.onNoteClick(noteViewModel);
    }

    @Override protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.row_note, parent, false);
    }
}
