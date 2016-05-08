package es.olidroide.notepad.notes.viewmodel;

import es.olidroide.notepad.notes.domain.Note;
import java.text.SimpleDateFormat;

public class NoteViewModel {

    private final String key;
    private final String note;
    private final String createdAt;

    public NoteViewModel(Builder builder) {
        this.key = builder.key;
        this.note = builder.note;
        this.createdAt = builder.createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
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
        private String createdAt;

        public Builder(Note note) {
            this.key = note.getKey();
            this.note = note.getNote();
            if (note.getCreatedAt() != null) {
                this.createdAt = SimpleDateFormat.getDateTimeInstance().format(note.getCreatedAt());
            } else {
                this.createdAt = "";
            }
        }

        public NoteViewModel build() {
            return new NoteViewModel(this);
        }
    }
}
