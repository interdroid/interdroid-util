package interdroid.util;

import java.io.File;

/**
 * A utility class for dealing with file systems.
 *
 * @author nick &lt;palmer@cs.vu.nl&gt;
 *
 */
public final class FSUtil {

	/**
	 * Remove a directory and all of the contents.
	 * @param directory the directory to remove
	 * @return true if it was deleted.
	 */
	public static boolean removeDirectory(File directory) {

		if (directory == null) {
			return false;
		}
		if (!directory.exists()) {
			return true;
		}
		if (!directory.isDirectory()) {
			return false;
		}

		String[] list = directory.list();

		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				File entry = new File(directory, list[i]);

				if (entry.isDirectory()) {
					if (!removeDirectory(entry)) {
						return false;
					}
				} else {
					if (!entry.delete()) {
						return false;
					}
				}
			}
		}

		return directory.delete();
	}

}
