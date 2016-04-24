package es.olidroide.notepad.main;

import dagger.Module;
import es.olidroide.notepad.main.view.NotepadActivity;
import es.olidroide.notepad.notes.view.NotesFragment;

@Module(library = true,
        complete = false,
        injects = {
            NotepadActivity.class
        })
public class NotepadModule {

}

