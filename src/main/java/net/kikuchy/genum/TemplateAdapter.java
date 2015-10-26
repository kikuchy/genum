package net.kikuchy.genum;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import net.kikuchy.genum.entity.EnumeratorMetaData;

import java.io.*;

/**
 * Created by kikuchy on 2015/10/26.
 */
public final class TemplateAdapter {
    private EnumeratorMetaData enumeratorMetaData;

    public TemplateAdapter(EnumeratorMetaData enumeratorMetaData) {
        this.enumeratorMetaData = enumeratorMetaData;
    }

    public void adapt(InputStream templateFileStream, OutputStream generatedCodeOutStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(generatedCodeOutStream);
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(new InputStreamReader(templateFileStream), enumeratorMetaData.getName());
        mustache.execute(writer, enumeratorMetaData);
        writer.flush();
    }
}
