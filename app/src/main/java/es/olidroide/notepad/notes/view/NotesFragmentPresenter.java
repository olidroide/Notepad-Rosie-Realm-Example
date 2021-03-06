package es.olidroide.notepad.notes.view;

import android.util.Log;
import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.domain.usecase.error.OnErrorCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.view.RosiePresenter;
import es.olidroide.notepad.notes.domain.GetNotes;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notepad.notes.domain.SaveNote;
import es.olidroide.notepad.notes.viewmodel.NoteToNoteViewModel;
import es.olidroide.notepad.notes.viewmodel.NoteViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class NotesFragmentPresenter extends RosiePresenter<NotesFragmentPresenter.View> {
    private static final int NUMBER_OF_NOTES_PER_PAGE = 15;

    private final SaveNote saveNote;
    private final GetNotes getNotes;
    private final NoteToNoteViewModel noteToNoteViewModel;

    private int offset = 0;

    @Inject public NotesFragmentPresenter(UseCaseHandler useCaseHandler, GetNotes getNotes, SaveNote saveNote,
        NoteToNoteViewModel noteToNoteViewModel) {
        super(useCaseHandler);

        this.noteToNoteViewModel = noteToNoteViewModel;
        this.getNotes = getNotes;
        this.saveNote = saveNote;
    }

    @Override public void initialize() {
        super.initialize();
    }

    //OnResume
    @Override public void update() {
        super.update();
        getView().showLoading();

        PaginatedCollection<Note> allNotesInCache = getNotes.getAllNotesInCache();
        if (allNotesInCache.getPage().getLimit() == 0) {
            loadNotes();
        } else {
            getView().clearCharacters();
            showNotes(allNotesInCache);
            offset = allNotesInCache.getItems().size();
        }
    }

    @Override public void pause() {
        super.pause();
    }

    @Override public void destroy() {
        super.destroy();
    }

    private void showNotes(PaginatedCollection<Note> notes) {
        saveNotes(new ArrayList<>(notes.getItems()));
        final List<NoteViewModel> noteViewModels = noteToNoteViewModel.mapNotesToNoteViewModels(notes);
        getView().updateNotes(noteViewModels);
        getView().showHasMore(notes.hasMore());
        getView().hideLoading();
    }

    private void loadNotes() {
        createUseCaseCall(getNotes).args(Page.withOffsetAndLimit(offset, NUMBER_OF_NOTES_PER_PAGE))
            .onSuccess(new OnSuccessCallback() {
                @Success public void onNotesLoaded(PaginatedCollection<Note> notes) {
                    showNotes(notes);
                    offset = notes.getPage().getOffset() + NUMBER_OF_NOTES_PER_PAGE;
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

    private void saveNotes(List<Note> notes) {
        createUseCaseCall(saveNote).args(notes).execute();
    }

    //FROM NoteRenderer
    public void onNoteClick(NoteViewModel noteViewModel) {
        Log.d(getClass().getCanonicalName(), "onNoteClick " + noteViewModel.toString());
        final Note note = new Note.Builder().setKey(noteViewModel.getKey())
            .setNote(String.valueOf(System.currentTimeMillis()))
            .build();

        createUseCaseCall(saveNote).args(note).onSuccess(new OnSuccessCallback() {
            @Success public void onNoteSaved() {
                loadNotes();
            }
        }).onError(new OnErrorCallback() {
            @Override public boolean onError(Error error) {
                return false;
            }
        }).execute();
    }

    public void onLoadMore() {
        loadNotes();
    }

    public interface View extends RosiePresenter.View {
        void updateNotes(List<NoteViewModel> notes);

        void hideLoading();

        void showLoading();

        void clearCharacters();

        void showHasMore(boolean b);
    }
}
