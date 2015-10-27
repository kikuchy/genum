package net.kikuchy.genum;

import net.kikuchy.genum.entity.EnumeratorMetaData;
import net.kikuchy.genum.entity.EnumeratorValue;

import java.io.InputStream;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by kikuchy on 2015/10/26.
 */
public interface SourceLoader {
    List<EnumeratorValue> parse(InputStream stream, String arrayPath) throws DataFormatException;
}
