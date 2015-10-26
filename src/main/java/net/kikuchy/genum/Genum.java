package net.kikuchy.genum;

import net.kikuchy.genum.entity.EnumeratorMetaData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    public void generate(OutputStream generatedCodeDestination) throws DataFormatException, IOException {
        EnumeratorMetaData metaData = sourceLoader.parse();
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
