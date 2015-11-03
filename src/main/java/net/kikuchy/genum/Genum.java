package net.kikuchy.genum;

import net.kikuchy.genum.entity.EnumeratorMetaData;
import net.kikuchy.genum.entity.EnumeratorValue;
import net.kikuchy.genum.internal.StringUtil;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by kikuchy on 2015/10/26.
 */
public final class Genum {
    private String packageName;
    private String className;
    private GenumOption option;
    private SourceLoader sourceLoader;

    public void generate(InputStream sourceArrayStream, OutputStream generatedCodeDestination)
            throws DataFormatException, IOException {
        List<EnumeratorValue> values = sourceLoader.parse(sourceArrayStream, "");
        EnumeratorMetaData metaData = new EnumeratorMetaData(packageName, className, values);
        SourceGenerator adapter = new SourceGenerator(metaData);
        adapter.adapt(generatedCodeDestination);
    }

    static class Builder {
        private String packageName;
        private String className;

        private GenumOption option;
        private SourceLoader sourceLoader;

        public Builder(String packageName, String className, SourceLoader sourceLoader) {
            this.className = className;
            this.packageName = packageName;
            this.sourceLoader = sourceLoader;
        }

        public Builder(String canonicalClassName, SourceLoader sourceLoader) {
            Pair<String, String> packClass = StringUtil.splitPackageNameAndClassName(canonicalClassName);
            this.className = packClass.getLeft();
            this.packageName = packClass.getRight();
            this.sourceLoader = sourceLoader;
        }

        public Builder setOption(GenumOption option) {
            this.option = option;
            return this;
        }

        public Genum build() {
            Genum genum = new Genum();
            genum.sourceLoader = sourceLoader;
            genum.packageName = packageName;
            genum.className = className;
            genum.option = option;
            return genum;
        }
    }
}
