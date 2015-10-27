package net.kikuchy.genum;

import net.kikuchy.genum.entity.EnumeratorMetaData;
import net.kikuchy.genum.entity.EnumeratorValue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by kikuchy on 2015/10/26.
 */
public final class Genum {
    private GenumOption option;
    private SourceLoader sourceLoader;
    private InputStream codeTemplateStream;

    public Genum(GenumOption option) {
        this.option = option;
    }

    public void generate(InputStream sourceArrayStream ,OutputStream generatedCodeDestination)
            throws DataFormatException, IOException {
        List<EnumeratorValue> values = sourceLoader.parse(sourceArrayStream, "");
        EnumeratorMetaData metaData = new EnumeratorMetaData(option.getCanonicalName(), values);
        TemplateAdapter adapter = new TemplateAdapter(metaData);
        adapter.adapt(codeTemplateStream, generatedCodeDestination);
    }

    protected void setSourceLoader(SourceLoader sourceLoader) {
        this.sourceLoader = sourceLoader;
    }

    protected void setCodeTemplateStream(InputStream codeTemplateStream) {
        this.codeTemplateStream = codeTemplateStream;
    }
}
