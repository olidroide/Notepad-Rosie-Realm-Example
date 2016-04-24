package es.olidroide.notepad.notes.viewmodel;

import es.olidroide.notepad.notes.domain.Note;

public class NoteViewModel {

    private final String key;
    private final String name;
    private final String note;

    public NoteViewModel(Builder builder) {
        this.key = builder.key;
        this.name = builder.name;
        this.note = builder.note;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public static class Builder {
        private String key;
        private String name;
        private String note;

        public Builder(Note note) {
            this.key = note.getKey();
            this.name = note.getName();
            this.note = note.getNote();
        }

        public NoteViewModel build() {
            return new NoteViewModel(this);
        }
    }
}
