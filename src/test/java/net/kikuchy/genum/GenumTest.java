package net.kikuchy.genum;

import net.kikuchy.genum.loader.LocalYamlLoader;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by kikuchy on 2015/11/03.
 */
public class GenumTest {
    private InputStream stringToStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    @Test
    public void testGenerate() throws Exception {
        String simpleYaml = "- hoge\n- fuga\n- piyo";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Genum genum = new Genum.Builder("com.example.Hoge", new LocalYamlLoader())
                .build();
        genum.generate(stringToStream(simpleYaml), outputStream);
        assertThat(outputStream.toString(), is("package com.example;\n\npublic enum Hoge {\n  HOGE,\n\n  FUGA,\n\n  PIYO\n}\n"));
    }
}