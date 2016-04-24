package es.olidroide.notepad.notes.view;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.domain.usecase.error.OnErrorCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.view.RosiePresenter;
import es.olidroide.notepad.notes.domain.GetNotes;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notepad.notes.viewmodel.NoteToNoteViewModel;
import es.olidroide.notepad.notes.viewmodel.NoteViewModel;
import java.util.List;
import javax.inject.Inject;

public class NotesFragmentPresenter extends RosiePresenter<NotesFragmentPresenter.View> {
    private static final int NUMBER_OF_NOTES_PER_PAGE = 15;

    private final GetNotes getNotes;
    private final NoteToNoteViewModel noteToNoteViewModel;

    private int offset = 0;

    @Inject public NotesFragmentPresenter(UseCaseHandler useCaseHandler, GetNotes getNotes,
        NoteToNoteViewModel noteToNoteViewModel) {
        super(useCaseHandler);

        this.noteToNoteViewModel = noteToNoteViewModel;
        this.getNotes = getNotes;
    }

    @Override public void initialize() {
        super.initialize();
    }

    @Override public void update() {
        super.update();
        getView().showLoading();

        PaginatedCollection<Note> allNotesInCache = getNotes.getAllNotesInCache();
        if (allNotesInCache.getPage().getLimit() == 0) {
            loadNotes();
        } else {
            //getView().clearCharacters();
            showNotes(allNotesInCache);
            //offset = allNotesInCache.getItems().size();
        }
    }

    private void showNotes(PaginatedCollection<Note> notes) {
        getView().updateNotes(noteToNoteViewModel.mapNotesToNoteViewModels(notes));
        getView().hideLoading();
    }

    private void loadNotes() {
        createUseCaseCall(getNotes).args(Page.withOffsetAndLimit(offset, NUMBER_OF_NOTES_PER_PAGE))
            .onSuccess(new OnSuccessCallback() {
                @Success public void onNotesLoaded(PaginatedCollection<Note> characters) {
                    showNotes(characters);
                    offset = characters.getPage().getOffset() + NUMBER_OF_NOTES_PER_PAGE;
                }
            })
            .onError(new OnErrorCallback() {
                @Override public boolean onError(Error error) {
                    getView().hideLoading();
                    return false;
                }
            })
            .execute();
    }

    @Override public void pause() {
        super.pause();
    }

    @Override public void destroy() {
        super.destroy();
    }

    public interface View extends RosiePresenter.View {
        void updateNotes(List<NoteViewModel> notes);

        void hideLoading();

        void showLoading();
    }
}
