package es.olidroide.notepad.notes.view.renderer;

import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;
import es.olidroide.notepad.notes.view.NotesFragmentPresenter;
import es.olidroide.notepad.notes.viewmodel.NoteViewModel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NoteRendererBuilder extends RendererBuilder<NoteViewModel> {
    private Map<Class, Class> rendererMapping = new HashMap<>();

    public NoteRendererBuilder(NotesFragmentPresenter presenter) {
        List<Renderer<NoteViewModel>> prototypes = new LinkedList<>();
        prototypes.add(new NoteRenderer(presenter));
        rendererMapping.put(NoteViewModel.class, NoteRenderer.class);
        prototypes.add(new LoadMoreNotesRenderer());
        setPrototypes(prototypes);
    }

    @Override protected Class getPrototypeClass(NoteViewModel content) {
        if (content != null) {
            return rendererMapping.get(content.getClass());
        } else {
            return LoadMoreNotesRenderer.class;
        }
    }
}
