package me.loki2302;

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
        noteService.createNote("note1", "hello");
        Note note = noteService.getNoteById("note1");
        assertEquals("note1", note.id);
        assertEquals("hello", note.text);
    }

    @Test
    public void canUpdateNote() {
        noteService.createNote("note1", "hello");
        noteService.updateNote("note1", "omg");
        Note note = noteService.getNoteById("note1");
        assertEquals("note1", note.id);
        assertEquals("omg", note.text);
    }

    @Test
    public void canDeleteNote() {
        noteService.createNote("note1", "hello");
        noteService.deleteNote("note1");
        Note note = noteService.getNoteById("note1");
        assertNull(note);
    }
}
