package es.olidroide.notepad.notes.repository.datasource;

import com.karumi.rosie.mapper.Mapper;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notesdatabaseclient.Note.NoteRealm;

public class NoteToNoteRealmMapper extends Mapper<Note, NoteRealm> {
    @Override public NoteRealm map(Note note) {
        final NoteRealm noteRealm = NoteRealm.Builder.create().setNote(note.getNote()).setId(note.getKey()).build();
        return noteRealm;
    }

    @Override public Note reverseMap(NoteRealm noteRealm) {
        final Note note = Note.Builder.create().setNote(noteRealm.getNote()).setKey(noteRealm.getId()).build();
        return note;
    }
}
