package me.loki2302;

import me.loki2302.commands.CreateNoteCommand;
import me.loki2302.commands.DeleteNoteCommand;
import me.loki2302.commands.UpdateNoteCommand;
import me.loki2302.queries.GetAllNoteQuery;
import me.loki2302.queries.GetByIdNoteQuery;
import me.loki2302.queries.NoteDTO;
import me.loki2302.queries.NoteQueryHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringApplicationConfiguration(classes = Config.class)
public class DummyTest {
    @Autowired
    private NoteServiceFacade noteServiceFacade;

    @Autowired
    private NoteQueryHandler noteQueryHandler;

    @Test
    public void canCreateNote() {
        noteServiceFacade.handleCommand(makeCreateNoteCommand("note1", "hello"));
        NoteDTO note = noteQueryHandler.handle(makeGetByIdNoteQuery("note1"));
        assertEquals("note1", note.id);
        assertEquals("hello", note.text);
        assertEquals(5, note.textLength);
    }

    @Test
    public void canUpdateNote() {
        noteServiceFacade.handleCommand(makeCreateNoteCommand("note1", "hello"));
        noteServiceFacade.handleCommand(makeUpdateNoteCommand("note1", "omg"));
        NoteDTO note = noteQueryHandler.handle(makeGetByIdNoteQuery("note1"));
        assertEquals("note1", note.id);
        assertEquals("omg", note.text);
        assertEquals(3, note.textLength);
    }

    @Test
    public void canDeleteNote() {
        noteServiceFacade.handleCommand(makeCreateNoteCommand("note1", "hello"));
        noteServiceFacade.handleCommand(makeDeleteNoteCommand("note1"));
        NoteDTO note = noteQueryHandler.handle(makeGetByIdNoteQuery("note1"));
        assertNull(note);
    }

    @Test
    public void canGetAllNotes() {
        noteServiceFacade.handleCommand(makeCreateNoteCommand("note1", "omg"));
        noteServiceFacade.handleCommand(makeCreateNoteCommand("note2", "wtf"));
        noteServiceFacade.handleCommand(makeCreateNoteCommand("note3", "bbq"));
        List<NoteDTO> noteDTOs = noteQueryHandler.handle(makeGetAllNoteQuery());
        assertEquals(3, noteDTOs.size());
    }

    private static CreateNoteCommand makeCreateNoteCommand(String id, String text) {
        CreateNoteCommand command = new CreateNoteCommand();
        command.id = id;
        command.text = text;
        return command;
    }

    private static UpdateNoteCommand makeUpdateNoteCommand(String id, String text) {
        UpdateNoteCommand command = new UpdateNoteCommand();
        command.id = id;
        command.text = text;
        return command;
    }

    private static DeleteNoteCommand makeDeleteNoteCommand(String id) {
        DeleteNoteCommand command = new DeleteNoteCommand();
        command.id = id;
        return command;
    }

    private static GetByIdNoteQuery makeGetByIdNoteQuery(String id) {
        GetByIdNoteQuery query = new GetByIdNoteQuery();
        query.id = id;
        return query;
    }

    private static GetAllNoteQuery makeGetAllNoteQuery() {
        return new GetAllNoteQuery();
    }
}
