package es.olidroide.notepad.notes.view.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.karumi.rosie.renderer.RosieRenderer;
import es.olidroide.notepad.R;
import es.olidroide.notepad.notes.viewmodel.NoteViewModel;

public class LoadMoreNotesRenderer extends RosieRenderer<NoteViewModel> {
    @Override protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.row_load_more_notes, parent, false);
    }
}
