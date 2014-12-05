package de.jgroeneveld;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppArgsTest {

    @Test
    public void hasErrorsWhenArgsEmpty() throws Exception {
        AppArgs appArgs = new AppArgs(new String[]{});

        assertTrue(appArgs.hasErrors());
        assertEquals(appArgs.getErrorMessage(), "sourceUrl is missing\nUsage: java -jar ImmonetPictureDownloader.jar sourceUrl[targetFolder]");
    }

    @Test
    public void hasNoErrorsWhenUrlGiven() throws Exception {
        AppArgs appArgs = new AppArgs(new String[]{"http://someurl.de"});

        assertFalse(appArgs.hasErrors());
        assertEquals(appArgs.getErrorMessage(), "");
    }

    @Test
    public void hasNoErrorsWhenUrlAndTargetFolderGiven() throws Exception {
        AppArgs appArgs = new AppArgs(new String[]{"http://someurl.de", "someCrazyFolder"});

        assertFalse(appArgs.hasErrors());
        assertEquals(appArgs.getErrorMessage(), "");
    }
}