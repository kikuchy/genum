package net.kikuchy.genum.entity;

import java.util.List;

/**
 * Created by kikuchy on 2015/10/26.
 */
public class EnumeratorMetaData {
    private List<EnumeratorValue> enums;
    private IdentityMetaData idMeta;
    private String className;
    private String packageName;

    public EnumeratorMetaData(String packageName, String className, List<EnumeratorValue> enums) {
        this(packageName, className, enums, new IdentityMetaData());
    }

    public EnumeratorMetaData(String packageName, String className, List<EnumeratorValue> enums, IdentityMetaData idMeta) {
        this.packageName = packageName;
        this.className = className;
        this.enums = enums;
        this.idMeta = idMeta;
    }

    public List<EnumeratorValue> getEnums() {
        return enums;
    }

    public IdentityMetaData getIdentityMetaData() {
        return idMeta;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }
}
