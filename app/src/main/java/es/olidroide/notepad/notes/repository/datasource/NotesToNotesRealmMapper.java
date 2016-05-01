package es.olidroide.notepad.notes.repository.datasource;

import com.karumi.rosie.mapper.Mapper;
import es.olidroide.notepad.notes.domain.Note;
import es.olidroide.notesdatabaseclient.Note.NoteRealm;
import java.util.ArrayList;
import java.util.List;

public class NotesToNotesRealmMapper extends Mapper<List<Note>, List<NoteRealm>> {
    @Override public List<NoteRealm> map(List<Note> noteList) {
        List<NoteRealm> noteRealmList = new ArrayList<>(noteList.size());

        NoteRealm noteRealm;
        for (Note note : noteList) {
            noteRealm = new NoteRealm().setId(note.getKey()).setNote(note.getNote());
            noteRealmList.add(noteRealm);
        }

        return noteRealmList;
    }

    @Override public List<Note> reverseMap(List<NoteRealm> value) {
        return null;
    }
}
