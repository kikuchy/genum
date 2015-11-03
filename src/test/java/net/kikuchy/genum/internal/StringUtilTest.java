package net.kikuchy.genum.internal;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by kikuchy on 2015/11/03.
 */
public class StringUtilTest {
    @Test
    public void testIsCanonicalClassName() throws Exception {
        assertTrue(StringUtil.isCanonicalClassName("com.example.Hoge"));
        assertFalse(StringUtil.isCanonicalClassName("com.example.hoge"));
        assertFalse(StringUtil.isCanonicalClassName(".Hoge"));
        assertFalse(StringUtil.isCanonicalClassName("com.example."));
    }

    @Test
    public void testSplitPackageNameAndClassName() throws Exception {
        Pair<String, String> result = StringUtil.splitPackageNameAndClassName("com.example.Hoge");
        assertThat(result.getLeft(), is("com.example"));
        assertThat(result.getRight(), is("Hoge"));
        result = StringUtil.splitPackageNameAndClassName("com.example.Hoge.Moge");
        assertThat(result.getLeft(), is("com.example.Hoge"));
        assertThat(result.getRight(), is("Moge"));
    }

    @Test
    public void testToUpperUnderscored() throws Exception {
        assertThat(StringUtil.toUpperUnderscored("hoge"), is("HOGE"));
        assertThat(StringUtil.toUpperUnderscored("hoge-hoge"), is("HOGE_HOGE"));
        assertThat(StringUtil.toUpperUnderscored("hogeHoge"), is("HOGE_HOGE"));
        assertThat(StringUtil.toUpperUnderscored("hoge_hoge"), is("HOGE_HOGE"));
        assertThat(StringUtil.toUpperUnderscored("HOGE_HOGE"), is("HOGE_HOGE"));
        assertThat(StringUtil.toUpperUnderscored("hoge hoge"), is("HOGE_HOGE"));
    }
}
