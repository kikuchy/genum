package net.kikuchy.genum.entity;

import net.kikuchy.genum.internal.StringUtil;

/**
 * Created by kikuchy on 2015/10/27.
 */
public class EnumeratorValue {
    private String value;

    public EnumeratorValue(String value) {
        this.value = StringUtil.toUpperUnderscored(value);
    }

    public String getValue() {
        return value;
    }
}
