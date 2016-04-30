package es.olidroide.notepad.notes.domain;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import com.karumi.rosie.repository.policy.WritePolicy;
import es.olidroide.notepad.notes.repository.NotesRepository;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;

public class SaveNote extends RosieUseCase {

    private final NotesRepository notesRepository;

    @Inject public SaveNote(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    //public void saveNoteInCache(Note note) {
    //    try {
    //        notesRepository.addOrUpdate(note, WritePolicy.WRITE_ONCE);
    //    }catch (Exception e){
    //        notifyError(new Error(e));
    //    }
    //
    //    //Collection<Note> all;
    //    //
    //    //try {
    //    //    all = notesRepository.getAll(ReadPolicy.CACHE_ONLY);
    //    //} catch (Exception e) {
    //    //    all = new ArrayList<>();
    //    //}
    //    //
    //    //if (all == null) {
    //    //    all = new ArrayList<>();
    //    //}
    //    //
    //    //Page page = Page.withOffsetAndLimit(0, all.size());
    //    //
    //    //PaginatedCollection<Note> notes = new PaginatedCollection<>(all);
    //    //notes.setPage(page);
    //    //notes.setHasMore(true);
    //
    //}

    @UseCase public void saveNote(Note note) throws Exception {
        notesRepository.addOrUpdate(note);
        notifySuccess(note);
    }
}
