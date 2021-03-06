package net.kikuchy.genum.loader;

import net.kikuchy.genum.SourceLoader;
import net.kikuchy.genum.entity.EnumeratorValue;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Genum default {@link SourceLoader}.
 * It loads string YAML file by stream (local file, standard input or etc... (maybe you can use network)).
 */
public class LocalYamlLoader implements SourceLoader {
    @Override
    public List<EnumeratorValue> parse(InputStream stream, String arrayPath) throws DataFormatException {
        Yaml yaml = new Yaml();
        Object parsed = yaml.load(stream);

        List<EnumeratorValue> enumNames = new ArrayList<EnumeratorValue>();

        if (!(parsed instanceof List)) {
            throw new DataFormatException("LocalYamlLoader can load only YAML that made with single array.");
        }
        List<Object> parsedList = (List<Object>) parsed;
        for (Object obj : parsedList) {
            if (obj instanceof String) {
                String value = (String) obj;
                enumNames.add(new EnumeratorValue(value));
            } else {
                throw new DataFormatException("Every array elements must be String.");
            }
        }
        return enumNames;
    }
}
