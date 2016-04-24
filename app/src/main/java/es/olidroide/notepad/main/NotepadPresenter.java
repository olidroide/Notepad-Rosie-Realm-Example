package es.olidroide.notepad.main;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.view.RosiePresenter;
import com.karumi.rosie.view.loading.RosiePresenterWithLoading;
import javax.inject.Inject;

public class NotepadPresenter extends RosiePresenter<NotepadPresenter.View> {

    @Inject public NotepadPresenter(UseCaseHandler useCaseHandler) {
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
