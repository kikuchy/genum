package net.kikuchy.genum.internal;


import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by kikuchy on 2015/11/03.
 */
public final class StringUtil {
    public static boolean isCanonicalClassName(String str) {
        int idx = str.lastIndexOf('.');
        return idx > 0 && (idx + 1) < str.length() && StringUtils.isAsciiPrintable(str) && isUpperCase(str.charAt(idx + 1));
    }

    public static boolean isUpperCase(int charCode) {
        return charCode >= 'A' && charCode <= 'Z';
    }

    public static Pair<String, String> splitPackageNameAndClassName(String str) {
        int idx = str.lastIndexOf('.');
        return Pair.of(str.substring(0, idx), str.substring(idx + 1));
    }

    public static String toUpperUnderscored(String str) {
        if (str.contains("-")) {
            // hyphenated
            return StringUtils.upperCase(StringUtils.replace(str, "-", "_"));
        } else if (str.contains(" ")) {
            // spaced
            return StringUtils.upperCase(StringUtils.replace(str, " ", "_"));
        } else if (str.contains("_")) {
            // upper underscored or lower underscored
            return StringUtils.upperCase(str);
        } else {
            // maybe upper camel or lower camel
            String lc = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, str);
            return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, lc);
        }
    }


}
