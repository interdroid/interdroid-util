package interdroid.util;

import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.ContentValues;
import android.database.Cursor;

public final class DbUtil {
    /**
     * Access to Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbUtil.class);

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
        if (columnName.charAt(0) != '\"') {
            return "\"" + columnName + "\"";
        }
        return columnName;
    }

    /**
     * Returns an array of quoted column names
     * @param columnNames the names of the columns to quote.
     * @return an array of quoted column names
     */
    public static String[] quoteColumnNames(final String[] columnNames) {
        String[] ret = null;
        if (columnNames != null) {
            ret = new String[columnNames.length];
            for (int i = 0; i < columnNames.length; i++) {
                ret[i] = quoteColumnName(columnNames[i]);
            }
        }
        return ret;
    }

    /**
     * Returns the index for a field.
     * @param c the cursor to check in
     * @param columnName the column name to look for
     * @return the index, or -1
     */
    public static int getFieldIndex(Cursor c, String columnName) {
        int index = c.getColumnIndex(columnName);
        if (index == -1) {
            index = c.getColumnIndex(quoteColumnName(columnName));
        }
        if (index == -1) {
            LOG.warn("Unable to find column index: {} in {}",
                    columnName, c.getColumnNames());
        }
        return index;
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
