package es.olidroide.notepad.main.view;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.view.RosiePresenter;
import com.karumi.rosie.view.loading.RosiePresenterWithLoading;
import javax.inject.Inject;

public class NotepadActivityPresenter extends RosiePresenter<NotepadActivityPresenter.View> {

    @Inject public NotepadActivityPresenter(UseCaseHandler useCaseHandler) {
        super(useCaseHandler);
    }

    @Override public void update() {
        super.update();
        getView().openNotesActivity();
    }

    public interface View extends RosiePresenterWithLoading.View {
        void openNotesActivity();
    }
}
