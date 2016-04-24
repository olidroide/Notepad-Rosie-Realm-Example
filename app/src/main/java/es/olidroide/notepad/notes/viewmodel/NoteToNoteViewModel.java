package es.olidroide.notepad.notes.viewmodel;

import com.karumi.rosie.repository.PaginatedCollection;
import es.olidroide.notepad.notes.domain.Note;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

public class NoteToNoteViewModel {

    @Inject public NoteToNoteViewModel() {

    }

    public List<NoteViewModel> mapNotesToNoteViewModels(PaginatedCollection<Note> notes) {
        List<NoteViewModel> noteViewModels = new LinkedList<>();

        NoteViewModel.Builder noteViewModelBuilder;

        for (Note note : notes.getItems()) {
            noteViewModelBuilder = new NoteViewModel.Builder(note);
            noteViewModels.add(noteViewModelBuilder.build());
        }

        return noteViewModels;
    }
}
