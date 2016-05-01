package es.olidroide.notepad.notes.repository.datasource;

import android.content.Context;
import com.karumi.rosie.repository.datasource.EmptyWriteableDataSource;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notesdatabaseclient.Note.NoteDatabaseClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

public class NotesWritableDatabaseSource extends EmptyWriteableDataSource<String, Note> {

    private final NoteDatabaseClient notesDatabaseClient;

    @Inject public NotesWritableDatabaseSource(Context context) {
        notesDatabaseClient = new NoteDatabaseClient(context);
    }

    @Override public Collection<Note> addOrUpdateAll(Collection<Note> values) {
        notesDatabaseClient.saveAll(new NotesToNotesRealmMapper().map(new ArrayList<>(values)));
        return super.addOrUpdateAll(values);
    }
}
