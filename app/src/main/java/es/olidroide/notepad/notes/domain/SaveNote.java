package es.olidroide.notepad.notes.domain;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import es.olidroide.notepad.notes.repository.NotesRepository;
import java.util.ArrayList;
import javax.inject.Inject;

public class SaveNote extends RosieUseCase {

    private final NotesRepository notesRepository;

    @Inject public SaveNote(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @UseCase public void saveNote(ArrayList<Note> notes) throws Exception {
        notesRepository.addOrUpdateAll(notes);
        notifySuccess();
    }

    @UseCase public void saveNote(Note note) throws Exception {
        notesRepository.addOrUpdate(note);
        notifySuccess(note);
    }
}
