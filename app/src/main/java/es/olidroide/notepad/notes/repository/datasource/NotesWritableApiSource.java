package es.olidroide.notepad.notes.repository.datasource;

import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import es.olidroide.Note.NoteApiClient;
import es.olidroide.Note.NoteDto;
import es.olidroide.NotesApiException;
import es.olidroide.notepad.notes.domain.Note;
import javax.inject.Inject;

public class NotesWritableApiSource extends EmptyWriteableDataSource<String, Note> {

    private final NoteApiClient noteApiClient;

    @Inject public NotesWritableApiSource(NoteApiClient noteApiClient) {
        this.noteApiClient = noteApiClient;
    }

    @Override public Note addOrUpdate(Note note) {
        final NoteToNoteDtoMapper noteToNoteDtoMapper = new NoteToNoteDtoMapper();
        NoteDto noteDto = null;

        try {
            noteDto = noteApiClient.postNote(noteToNoteDtoMapper.map(note));
        } catch (NotesApiException e) {
            e.printStackTrace();
        }

        return noteToNoteDtoMapper.reverseMap(noteDto);
    }

    //@Override public Collection<Note> addOrUpdateAll(Collection<Note> values) {
    //    notesDatabaseClient.saveAll(new NotesToNotesRealmMapper().map(new ArrayList<>(values)));
    //    return super.addOrUpdateAll(values);
    //}
}
