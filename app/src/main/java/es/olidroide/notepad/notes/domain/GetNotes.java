package es.olidroide.notepad.notes.domain;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import es.olidroide.notepad.notes.repository.NotesRepository;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;

public class GetNotes extends RosieUseCase {

    private final NotesRepository notesRepository;

    @Inject public GetNotes(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public PaginatedCollection<Note> getAllNotesInCache() {

        Collection<Note> all;
        try {
            all = notesRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            all = new ArrayList<>();
        }

        if (all == null) {
            all = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, all.size());

        PaginatedCollection<Note> comics = new PaginatedCollection<>(all);
        comics.setPage(page);
        comics.setHasMore(true);

        return comics;
    }

    @UseCase public void getNotes(Page page) throws Exception {
        PaginatedCollection<Note> notes = notesRepository.getPage(page);
        notifySuccess(notes);
    }
}
