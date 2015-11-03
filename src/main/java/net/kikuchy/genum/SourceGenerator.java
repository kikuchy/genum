package net.kikuchy.genum;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import net.kikuchy.genum.entity.EnumeratorMetaData;
import net.kikuchy.genum.entity.EnumeratorValue;

import java.io.*;

/**
 * Created by kikuchy on 2015/10/26.
 */
public final class SourceGenerator {
    private EnumeratorMetaData enumeratorMetaData;

    public SourceGenerator(EnumeratorMetaData enumeratorMetaData) {
        this.enumeratorMetaData = enumeratorMetaData;
    }

    public void adapt(OutputStream generatedCodeOutStream) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(generatedCodeOutStream);
        TypeSpec.Builder builder = TypeSpec.enumBuilder(enumeratorMetaData.getClassName());
        for (EnumeratorValue v : enumeratorMetaData.getEnums()) {
            builder.addEnumConstant(v.getValue());
        }
        TypeSpec out = builder.build();
        JavaFile file = JavaFile.builder(enumeratorMetaData.getPackageName(), out).build();
        file.writeTo(writer);
        writer.flush();
    }
}
