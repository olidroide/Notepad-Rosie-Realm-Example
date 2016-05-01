package es.olidroide.notesdatabaseclient.Note;

import android.content.Context;
import es.olidroide.notesdatabaseclient.NotesDatabaseClient;
import es.olidroide.notesdatabaseclient.NotesDatabaseException;
import io.realm.RealmResults;

public class NoteDatabaseClient extends NotesDatabaseClient {

    public NoteDatabaseClient(Context context) {
        super(context);
    }

    //public void saveNote(final NoteRealm noteRealm) {
    //    Realm.Transaction transaction = new Realm.Transaction() {
    //        @Override public void execute(Realm realm) {
    //            NoteRealm noteRealmObject = realm.createObject(NoteRealm.class);
    //            noteRealm.from(noteRealm);
    //        }
    //    };
    //
    //    execute(transaction);
    //}

    public NoteRealm getNote(String key) {
        return getByKey(NoteRealm.class, key);
    }

    public NotesRealm getAll(int offset, int limit) throws NotesDatabaseException {
        NotesRealm notesRealm = new NotesRealm();
        RealmResults<NoteRealm> noteList = getAll(NoteRealm.class);
        notesRealm.setNotes(noteList.subList(0, noteList.size()));

        return notesRealm;
    }
}
