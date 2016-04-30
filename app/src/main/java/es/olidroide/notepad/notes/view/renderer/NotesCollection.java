package es.olidroide.notepad.notes.view.renderer;

import com.pedrogomez.renderers.ListAdapteeCollection;
import es.olidroide.notepad.notes.viewmodel.NoteViewModel;

public class NotesCollection extends ListAdapteeCollection<NoteViewModel> {
    private boolean isShowLoadMore = true;

    public void setShowLoadMore(boolean showLoadMore) {
        isShowLoadMore = showLoadMore;
    }

    @Override public int size() {
        int size = super.size();
        return isShowLoadMore ? size + 1 : size;
    }

    @Override public NoteViewModel get(int position) {
        NoteViewModel noteViewModel = null;

        if (position < super.size()) {
            noteViewModel = super.get(position);
        }

        return noteViewModel;
    }
}
