package es.olidroide.notesdatabaseclient.Note;

import java.util.List;

public class NotesRealm {
    private List<NoteRealm> notes;

    public List<NoteRealm> getNotes() {
        return notes;
    }

    public NotesRealm setNotes(List<NoteRealm> notes) {
        this.notes = notes;
        return this;
    }
}
