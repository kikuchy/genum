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
 * Generate Java Enum class file from arrayed data source.
 * Genum needs package name, enum's class name, data source and java file's destination.
 * <p>
 * Genum can use custom {@link SourceLoader}, so you can use JSON or other format if you make the custom loader.
 * </p>
 */
public final class Genum {
    private String packageName;
    private String className;
    private GenumOption option;
    private SourceLoader sourceLoader;

    /**
     * Execute generation of java enum source code.
     *
     * @param sourceArrayStream        {@link InputStream} of arrayed data source.
     * @param generatedCodeDestination {@link OutputStream} that generated java enum source code will be written.
     * @throws DataFormatException Thrown if data source format is not supported.
     * @throws IOException         If streams not worked well.
     */
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
