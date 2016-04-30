package es.olidroide.notepad.notes.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;
import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.RosieFragment;
import com.karumi.rosie.view.paginated.ScrollToBottomListener;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;
import es.olidroide.notepad.R;
import es.olidroide.notepad.notes.view.renderer.NoteRendererBuilder;
import es.olidroide.notepad.notes.view.renderer.NotesCollection;
import es.olidroide.notepad.notes.viewmodel.NoteViewModel;
import java.util.List;
import javax.inject.Inject;

public class NotesFragment extends RosieFragment implements NotesFragmentPresenter.View {

    @Bind(R.id.fragment_notes_text) TextView notesTextView;
    @Bind(R.id.fragment_notes_progress) ProgressBar progressBar;
    @Bind(R.id.fragment_notes_recycler_view) RecyclerView recyclerViewNotes;

    @Inject
    @Presenter
    NotesFragmentPresenter notesFragmentPresenter;

    private NotesCollection notesCollection;
    private RVRendererAdapter<NoteViewModel> notesAdapter;
    private ScrollToBottomListener loadMoreListener;

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeNotesView();
    }

    private void initializeNotesView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewNotes.setHasFixedSize(true);
        recyclerViewNotes.setLayoutManager(layoutManager);

        initializeAdapter();
        recyclerViewNotes.setAdapter(notesAdapter);

        Drawable dividerDrawable = ContextCompat.getDrawable(recyclerViewNotes.getContext(), R.drawable.notes_divider);
        recyclerViewNotes.addItemDecoration(new DividerItemDecoration(dividerDrawable));

        loadMoreListener = new ScrollToBottomListener(layoutManager, new ScrollToBottomListener.Listener() {
            @Override public void onScrolledToBottom() {
                notesFragmentPresenter.onLoadMore();
            }
        });
        recyclerViewNotes.addOnScrollListener(loadMoreListener);
    }

    private void initializeAdapter() {
        RendererBuilder<NoteViewModel> rendererBuilder = new NoteRendererBuilder(notesFragmentPresenter);
        notesCollection = new NotesCollection();
        notesAdapter = new RVRendererAdapter<>(rendererBuilder, notesCollection);
    }

    @Override protected int getLayoutId() {
        return R.layout.fragment_notes;
    }

    @Override protected void onPreparePresenter() {
        super.onPreparePresenter();
    }

    @Override public void updateNotes(List<NoteViewModel> notes) {
        notesAdapter.addAll(notes);
        notesAdapter.notifyDataSetChanged();
    }

    @Override public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void clearCharacters() {
        notesAdapter.clear();
    }

    @Override public void showHasMore(boolean hasMore) {
        notesCollection.setShowLoadMore(hasMore);
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }
}
