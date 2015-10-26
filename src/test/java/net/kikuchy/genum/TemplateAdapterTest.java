package net.kikuchy.genum;

import net.kikuchy.genum.entity.EnumeratorMetaData;
import net.kikuchy.genum.entity.EnumeratorValue;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by kikuchy on 2015/10/27.
 */
public class TemplateAdapterTest {
    @Test
    public void testAdapt() throws Exception {
        String simpleTemplate = "public class {{name}} { {{#enums}}{{value}}{{/enums}}; }";
        ByteArrayInputStream is = new ByteArrayInputStream(simpleTemplate.getBytes());
        EnumeratorMetaData simpleData = new EnumeratorMetaData("Hoge", Arrays.asList(new EnumeratorValue("hoge")));

        TemplateAdapter adapter = new TemplateAdapter(simpleData);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        adapter.adapt(is, os);
        assertThat(os.toString(), is("public class Hoge { hoge; }"));
    }
}