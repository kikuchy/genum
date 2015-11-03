package net.kikuchy.genum;

import net.kikuchy.genum.entity.EnumeratorMetaData;
import net.kikuchy.genum.entity.EnumeratorValue;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by kikuchy on 2015/10/27.
 */
public class SourceGeneratorTest {
    @Test
    public void testAdapt() throws Exception {
        EnumeratorMetaData simpleData = new EnumeratorMetaData("com.example", "Hoge", Arrays.asList(new EnumeratorValue("hoge")));

        SourceGenerator adapter = new SourceGenerator(simpleData);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        adapter.adapt(os);
        assertThat(os.toString(), is("package com.example;\n" +
                "\n" +
                "public enum Hoge {\n" +
                "  HOGE\n" +
                "}\n"));
    }
}