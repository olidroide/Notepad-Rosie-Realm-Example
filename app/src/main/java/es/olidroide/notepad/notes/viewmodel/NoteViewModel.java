package es.olidroide.notepad.notes.viewmodel;

import es.olidroide.notepad.notes.domain.Note;

public class NoteViewModel {

    private final String key;
    private final String note;

    public NoteViewModel(Builder builder) {
        this.key = builder.key;
        this.note = builder.note;
    }

    public String getKey() {
        return key;
    }

    @Override public String toString() {
        return note;
    }

    public String getNote() {
        return note;
    }

    public static class Builder {
        private String key;
        private String note;

        public Builder(Note note) {
            this.key = note.getKey();
            this.note = note.getNote();
        }

        public NoteViewModel build() {
            return new NoteViewModel(this);
        }
    }
}
