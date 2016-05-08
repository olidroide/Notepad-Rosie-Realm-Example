package es.olidroide.notepad.notes.domain;

import android.text.TextUtils;
import com.karumi.rosie.repository.datasource.Identifiable;
import java.util.Date;

public class Note implements Identifiable<String> {
    private String key;
    private Date createdAt;
    private String note;

    public Note(Builder builder) {
        key = builder.key;
        createdAt = builder.createdAt;
        note = builder.note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override public String getKey() {
        return key;
    }

    public Note setKey(String key) {
        this.key = key;
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
        private Date createdAt;
        private String note;

        public static Builder create() {
            return new Builder();
        }

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setNote(String note) {
            this.note = note;
            return this;
        }

        public Builder setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setNote(Note note) {
            this.key = note.getKey();
            this.createdAt = note.getCreatedAt();
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
