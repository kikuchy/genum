package net.kikuchy.genum;

import java.io.InputStream;

/**
 * Created by kikuchy on 2015/10/26.
 */
public class GenumBuilder {
    private SourceLoader sourceLoader;
    private InputStream codeTemplateStream;
    private GenumOption genumOption;

    public GenumBuilder setSourceLoader(SourceLoader sourceLoader) {
        this.sourceLoader = sourceLoader;
        return this;
    }

    public GenumBuilder setCodeTemplateStream(InputStream codeTemplateStream) {
        this.codeTemplateStream = codeTemplateStream;
        return this;
    }

    public GenumBuilder setGenumOption(GenumOption genumOption) {
        this.genumOption = genumOption;
        return this;
    }

    public Genum build() {
        Genum genum = new Genum(genumOption);
        genum.setSourceLoader(sourceLoader);
        genum.setCodeTemplateStream(codeTemplateStream);
        return genum;
    }
}
