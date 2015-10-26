package net.kikuchy.genum.entity;

import java.util.List;

/**
 * Created by kikuchy on 2015/10/26.
 */
public class EnumeratorMetaData {
    private List<EnumeratorValue> enums;
    private IdentityMetaData idMeta;
    private String name;

    public EnumeratorMetaData(String name, List<EnumeratorValue> enums) {
        this(name, enums, new IdentityMetaData());
    }

    public EnumeratorMetaData(String name, List<EnumeratorValue> enums, IdentityMetaData idMeta) {
        this.name = name;
        this.enums = enums;
        this.idMeta = idMeta;
    }

    public List<EnumeratorValue> getEnums() {
        return enums;
    }

    public IdentityMetaData getIdentityMetaData() {
        return idMeta;
    }

    public String getName() {
        return name;
    }
}
