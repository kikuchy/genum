package net.kikuchy.genum;

import net.kikuchy.genum.entity.EnumeratorMetaData;

import java.util.zip.DataFormatException;

/**
 * Created by kikuchy on 2015/10/26.
 */
public interface SourceLoader {
    EnumeratorMetaData parse() throws DataFormatException;
}
