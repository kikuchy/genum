package net.kikuchy.genum;

import net.kikuchy.genum.entity.EnumeratorValue;

import java.io.InputStream;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Interface class that load data source and abstract it.
 * If you want to use not YAML data source such as JSON, CSV, etc... , extend this interface and make your loader class.
 */
public interface SourceLoader {
    /**
     * SourceLoader will parse data source and return list of enum values (enum constant names).
     * @param stream    Data source stream that given by Genum.generate() 's argument.
     * @param arrayPath Path for the object array in data source. SourceLoader should be trace the data source and extract the array.
     * @return List of abstracted enum values. Use {@link EnumeratorValue} for abstract.
     * @throws DataFormatException If data source format is not expected format, throw {@link DataFormatException}.
     */
    List<EnumeratorValue> parse(InputStream stream, String arrayPath) throws DataFormatException;
}
