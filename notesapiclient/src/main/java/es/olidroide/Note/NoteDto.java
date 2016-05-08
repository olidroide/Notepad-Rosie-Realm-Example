package es.olidroide.Note;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class NoteDto {

    @SerializedName("id") private String id;
    @SerializedName("note") private String note;
    @SerializedName("created_at") private Date createdAt;

    public NoteDto(Builder builder) {
        this.id = builder.id;
        this.note = builder.note;
    }

    public Date getCreatedAt() {
        return createdAt;
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
