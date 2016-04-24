package es.olidroide.notepad.notes.domain;

import android.text.TextUtils;
import com.karumi.rosie.repository.datasource.Identifiable;

public class Note implements Identifiable<String> {
    private String key;
    private String name;
    private String note;

    public Note(Builder builder) {
        key = builder.key;
        name = builder.name;
        note = builder.note;
    }

    @Override public String getKey() {
        return key;
    }

    public Note setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public Note setName(String name) {
        this.name = name;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Note setNote(String note) {
        this.note = note;
        return this;
    }

    public static class Builder {
        private String key;
        private String name;
        private String note;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setNote(String note) {
            this.note = note;
            return this;
        }

        public Builder setNote(Note note) {
            this.key = note.getKey();
            this.name = note.getName();
            this.note = note.getNote();

            return this;
        }

        public Note build() {
            if (TextUtils.isEmpty(note)) {
                throw new IllegalArgumentException("note can't  be empty");
            }

            return new Note(this);
        }
    }
}
