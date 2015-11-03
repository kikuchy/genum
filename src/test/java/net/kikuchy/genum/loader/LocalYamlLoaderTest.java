package net.kikuchy.genum.loader;

import net.kikuchy.genum.SourceLoader;
import net.kikuchy.genum.entity.EnumeratorValue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.DataFormatException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by kikuchy on 2015/10/27.
 */
public class LocalYamlLoaderTest {
    private InputStream stringToStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testRightCaseParse() throws Exception {
        String simpleYaml = "- hoge\n- fuga\n- piyo";
        SourceLoader loader = new LocalYamlLoader();
        List<EnumeratorValue> values = loader.parse(stringToStream(simpleYaml), "");
        assertThat(values.size(), is(3));
        assertThat(values.get(0).getValue(), is("HOGE"));
    }

    @Test
    public void testInvalidCaseParse() throws Exception {
        String numberYaml = "- 2\n- 5\n- 18";
        String notArrayYaml = "Hoge: h\nFuga: f\nMoge: m";
        SourceLoader loader = new LocalYamlLoader();

        exception.expect(DataFormatException.class);
        List<EnumeratorValue> values = loader.parse(stringToStream(numberYaml), "");
        values = loader.parse(stringToStream(notArrayYaml), "");
    }
}