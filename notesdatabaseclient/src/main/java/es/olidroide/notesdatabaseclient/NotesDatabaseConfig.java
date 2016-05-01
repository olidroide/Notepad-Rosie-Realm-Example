package es.olidroide.notesdatabaseclient;

import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NotesDatabaseConfig {

    private final Realm realm;

    public NotesDatabaseConfig(Builder builder) {
        this.realm = builder.realm;
    }

    public static NotesDatabaseConfig with(Context context) {
        return new Builder(context).build();
    }

    public Realm getRealm() {
        return realm;
    }

    public static class Builder {
        private RealmConfiguration realmConfig;
        private Realm realm;

        public Builder(Context context) {
            realmConfig = new RealmConfiguration.Builder(context).build();
        }

        public NotesDatabaseConfig build() {
            realm = Realm.getInstance(realmConfig);

            return new NotesDatabaseConfig(this);
        }
    }
}
