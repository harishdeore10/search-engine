import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import service.SearchService;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainTest {


    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test(expected = IllegalArgumentException.class)
    public void testException() throws IOException {
        Main.main(new String[]{});
    }

    @Test
    public void systemExitTest() throws IOException {
        exit.expectSystemExitWithStatus(0);
        String input2 = ":quit";
        InputStream in2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in2);
        Main.main(new String[]{"src/test/resources"});
    }
}
