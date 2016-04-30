package es.olidroide.Note;

import com.google.gson.annotations.SerializedName;

public class NoteDto {

    @SerializedName("id") private String id;
    @SerializedName("note") private String note;

    public NoteDto(Builder builder) {
        this.id = builder.id;
        this.note = builder.id;
    }

    public String getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    @Override public String toString() {
        return "NoteDto{" +
            "id='" + id + '\'' +
            ", note='" + note + '\'' +
            '}';
    }

    public static class Builder {
        private String id;
        private String note;

        public static Builder create() {
            return new Builder();
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setNote(String note) {
            this.note = note;
            return this;
        }

        public NoteDto build() {
            return new NoteDto(this);
        }
    }
}
