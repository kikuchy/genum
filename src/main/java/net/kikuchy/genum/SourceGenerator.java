package net.kikuchy.genum;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import net.kikuchy.genum.entity.EnumeratorMetaData;
import net.kikuchy.genum.entity.EnumeratorValue;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Generate java enum source code from abstracted data source.
 */
public final class SourceGenerator {
    private EnumeratorMetaData enumeratorMetaData;

    public SourceGenerator(EnumeratorMetaData enumeratorMetaData) {
        this.enumeratorMetaData = enumeratorMetaData;
    }

    public void adapt(OutputStream generatedCodeOutStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(generatedCodeOutStream);
        TypeSpec.Builder builder = TypeSpec.enumBuilder(enumeratorMetaData.getClassName());
        builder.addModifiers(Modifier.PUBLIC);
        for (EnumeratorValue v : enumeratorMetaData.getEnums()) {
            builder.addEnumConstant(v.getValue());
        }
        TypeSpec out = builder.build();
        JavaFile file = JavaFile.builder(enumeratorMetaData.getPackageName(), out).build();
        file.writeTo(writer);
        writer.flush();
    }
}
