package interdroid.util;

import java.util.Map.Entry;

import android.content.ContentValues;

public final class DbUtil {

    /**
     * No construction.
     */
    private DbUtil() { }

    /**
     * Returns a quoted column name if the column is not already quoted.
     * @param columnName the name of the column to quote
     * @return the quoted column name
     */
    public static String quoteColumnName(final String columnName) {
        if (columnName.charAt(0) != '\'') {
            return "'" + columnName + "'";
        }
        return columnName;
    }


    /**
     * Android does not quote identifiers so we have to handle that.
     * This returns a new ContentValues where the columns have all been
     * quoted properly.
     *
     * @param values the values to be quoted
     * @return a sanitized version of the values with quoted column names
     */
    public static ContentValues quoteColumnNames(final ContentValues values) {
        ContentValues cleanValues = new ContentValues();
        for (Entry<String, Object> val : values.valueSet()) {
            Object value = val.getValue();
            String cleanName = val.getKey();
            cleanName = quoteColumnName(cleanName);
            // This really sucks. There is no generic put an object....
            if (value == null) {
                cleanValues.putNull(cleanName);
            } else if (value instanceof Boolean) {
                cleanValues.put(cleanName, (Boolean) value);
            } else if (value instanceof Byte) {
                cleanValues.put(cleanName, (Byte) value);
            } else if (value instanceof byte[]) {
                cleanValues.put(cleanName, (byte[]) value);
            } else if (value instanceof Double) {
                cleanValues.put(cleanName, (Double) value);
            } else if (value instanceof Float) {
                cleanValues.put(cleanName, (Float) value);
            } else if (value instanceof Integer) {
                cleanValues.put(cleanName, (Integer) value);
            } else if (value instanceof Long) {
                cleanValues.put(cleanName, (Long) value);
            } else if (value instanceof Short) {
                cleanValues.put(cleanName, (Short) value);
            } else if (value instanceof String) {
                cleanValues.put(cleanName, (String) value);
            } else {
                throw new RuntimeException(
                        "Don't know how to add value of type: "
                                + value.getClass().getCanonicalName());
            }
        }
        return cleanValues;
    }
}
