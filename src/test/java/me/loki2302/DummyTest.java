package me.loki2302;

import me.loki2302.commands.CreateNoteCommand;
import me.loki2302.commands.DeleteNoteCommand;
import me.loki2302.commands.UpdateNoteCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringApplicationConfiguration(classes = Config.class)
public class DummyTest {
    @Autowired
    private NoteService noteService;

    @Test
    public void canCreateNote() {
        noteService.handleCommand(makeCreateNoteCommand("note1", "hello"));
        Note note = noteService.getNoteById("note1");
        assertEquals("note1", note.id);
        assertEquals("hello", note.text);
    }

    @Test
    public void canUpdateNote() {
        noteService.handleCommand(makeCreateNoteCommand("note1", "hello"));
        noteService.handleCommand(makeUpdateNoteCommand("note1", "omg"));
        Note note = noteService.getNoteById("note1");
        assertEquals("note1", note.id);
        assertEquals("omg", note.text);
    }

    @Test
    public void canDeleteNote() {
        noteService.handleCommand(makeCreateNoteCommand("note1", "hello"));
        noteService.handleCommand(makeDeleteNoteCommand("note1"));
        Note note = noteService.getNoteById("note1");
        assertNull(note);
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
}
