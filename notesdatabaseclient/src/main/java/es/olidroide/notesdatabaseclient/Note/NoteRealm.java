package es.olidroide.notesdatabaseclient.Note;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import java.util.Date;

public class NoteRealm extends RealmObject {

    @PrimaryKey private String id;
    @Required private String note;
    private Date createdAt;

    //Required by Realm
    public NoteRealm() {

    }

    public NoteRealm(Builder builder) {
        this.id = builder.id;
        this.note = builder.note;
        this.createdAt = builder.createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public NoteRealm setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getId() {
        return id;
    }

    public NoteRealm setId(String id) {
        this.id = id;
        return this;
    }

    public String getNote() {
        return note;
    }

    public NoteRealm setNote(String note) {
        this.note = note;
        return this;
    }

    public void from(NoteRealm noteRealm) {
        this.id = noteRealm.getId();
        this.note = noteRealm.getNote();
    }

    public static class Builder {
        private String id;
        private String note;
        private Date createdAt;

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

        public Builder setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public NoteRealm build() {
            return new NoteRealm(this);
        }
    }
}
