package FileFolderDisplay;

import java.io.File;

public class ShowFoldersAndFiles {
	public ShowFoldersAndFiles() {

	}

	public String displayFiles(File folder, int numOfIndetns,
			StringBuilder display) {
		File[] fileList = folder.listFiles();
		for (File currentFile : fileList) {
			for (int i = 0; i < numOfIndetns; i++) {
				display.append("\t");
			}
			display.append(String.format("%-15s", currentFile.getName()));
			if (currentFile.isDirectory()) {
				display.append("\n");
				displayFiles(currentFile, ++numOfIndetns, display);
				// why do i need to move the indent back, shouldn't it finish
				// the inner method
				// and return to the method that called it - and that method
				// should have a smaller
				// numOfIndents?
				numOfIndetns--;
			} else { // only display size for files not folders - just like my
				// computer does in windows
				display.append("\t");
				display.append(currentFile.length() / 1024);
				display.append(" KB");
				display.append("\n");
			}

		}

		return display.toString();

	}

	

}
