package es.olidroide.notesdatabaseclient;

import io.realm.RealmAsyncTask;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class NotesDatabaseClient {

    private final NotesDatabaseConfig notesDatabaseConfig;
    private RealmAsyncTask asyncTask;

    public NotesDatabaseClient(NotesDatabaseConfig notesDatabaseConfig) {
        this.notesDatabaseConfig = notesDatabaseConfig;
    }
    //
    //public void execute(Realm.Transaction transaction) {
    //
    //    Realm.Transaction.OnError callbackError = new Realm.Transaction.OnError() {
    //        @Override public void onError(Throwable error) {
    //
    //        }
    //    };
    //
    //    asyncTask = notesDatabaseConfig.getRealm().executeTransactionAsync(transaction, callbackError);
    //}

    public <T extends RealmObject> T save(T object) {
        notesDatabaseConfig.getRealm().beginTransaction();
        T saved = notesDatabaseConfig.getRealm().copyToRealm(object);
        notesDatabaseConfig.getRealm().commitTransaction();

        return saved;
    }

    public <T extends RealmObject> T getByKey(Class<T> clazz, String key) {
        return notesDatabaseConfig.getRealm().where(clazz).equalTo("id", key).findFirst();
    }

    public <T extends RealmObject> RealmResults<T> getAll(Class<T> clazz) {
        RealmResults<T> results = notesDatabaseConfig.getRealm().where(clazz).findAll();
        return results;
    }

    public void onStop() {
        if (asyncTask != null && !asyncTask.isCancelled()) {
            asyncTask.cancel();
        }
    }
}
