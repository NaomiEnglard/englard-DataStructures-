package FileFolderDisplay;

import java.io.File;

public class FolderHeirarcyMain {

	public static void main (String args[]){
		File address = new File("C:\\Users\\Naami\\Desktop\\touro");
		ShowFoldersAndFiles touroFolder = new ShowFoldersAndFiles();
		System.out.println(touroFolder.displayFiles(address, 0, new StringBuilder()));
	}
}
