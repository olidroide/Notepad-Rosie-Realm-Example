package es.olidroide.notepad.notes.repository.datasource;

import com.karumi.rosie.mapper.Mapper;
import es.olidroide.Note.NoteDto;
import es.olidroide.notepad.notes.domain.Note;

public class NoteToNoteDtoMapper extends Mapper<Note, NoteDto> {
    @Override public NoteDto map(Note note) {
        final NoteDto noteDto = NoteDto.Builder.create().setNote(note.getNote()).setId(note.getKey()).build();
        return noteDto;
    }

    @Override public Note reverseMap(NoteDto noteDto) {
        final Note note = Note.Builder.create().setNote(noteDto.getNote()).setKey(noteDto.getId()).build();
        return note;
    }
}
