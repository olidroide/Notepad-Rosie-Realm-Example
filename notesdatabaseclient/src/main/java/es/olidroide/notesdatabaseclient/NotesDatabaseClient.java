package es.olidroide.notesdatabaseclient;

import android.content.Context;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import java.util.List;

public class NotesDatabaseClient {

    private final Context context;
    private RealmAsyncTask asyncTask;

    public NotesDatabaseClient(Context context) {
        this.context = context;
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

    private Realm getRealm() {
        return NotesDatabaseConfig.with(context).getRealm();
    }

    public <T extends RealmObject> T save(T object) {
        Realm realm = getRealm();
        realm.beginTransaction();
        T saved = realm.copyToRealm(object);
        realm.commitTransaction();

        return saved;
    }

    public <T extends RealmObject> T getByKey(Class<T> clazz, String key) {
        return getRealm().where(clazz).equalTo("id", key).findFirst();
    }

    public <T extends RealmObject> RealmResults<T> getAll(Class<T> clazz) {
        RealmResults<T> results = getRealm().allObjects(clazz);
        return results;
    }

    public <T extends RealmModel> List<T> saveAll(List<T> list) {
        final Realm realm = getRealm();
        realm.beginTransaction();
        List<T> results = realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
        return results;
    }

    public void onStop() {
        if (asyncTask != null && !asyncTask.isCancelled()) {
            asyncTask.cancel();
        }
    }
}
