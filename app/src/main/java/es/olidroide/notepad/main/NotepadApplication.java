package es.olidroide.notepad.main;

import com.karumi.rosie.application.RosieApplication;
import java.util.Arrays;
import java.util.List;

public class NotepadApplication extends RosieApplication {
    @Override protected List<Object> getApplicationModules() {
        return Arrays.asList((Object) new NotepadApplicationModule());
    }
}