package mergeSort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class SortFile {

	private String unosortedFileName;
	private String sortedFileName;

	public SortFile(String name) {
		unosortedFileName = name;
		sortedFileName = null;
	}

	public String getSortedFileName() throws IOException {
		if (sortedFileName == null) {
			sort();
		}
		return this.sortedFileName;
	}

	private void sort() throws IOException {
		Scanner input = new Scanner(new File(this.unosortedFileName));
		int size = input.nextInt();
		// sort by dividing into subfiles and mergesort
		int qtyPerFile = numOfSubfile(size);
		int[] subList = new int[qtyPerFile];
		int qtyFilled = 0;
		int fileNum = 1; //
		while (input.hasNext()) {
			// read in a portion and of the file
			subList[qtyFilled++] = input.nextInt();
			if (qtyFilled == qtyPerFile) {
				// sort it
				sortSubList(subList, 0, qtyPerFile - 1);
				// send it to a new file
				writeOutSubList(subList, qtyPerFile, fileNum++);
				// reset qtyfilled adn sublist
				subList = new int[qtyPerFile];
				qtyFilled = 0;
			}
		}

		mergeSortSubLists(--fileNum);
	}

	private int numOfSubfile(int size) {
		// Decide subfile size by the number of digits in size - 2 places (1000
		// is 4 digits so it would be 10)
		int numOfZeros = String.valueOf(size).length() - 2;
		StringBuilder createFileSize = new StringBuilder();
		createFileSize.append(1);
		for (int i = 0; i < numOfZeros; i++) {
			createFileSize.append(0);
		}
		int qtyPerFile = Integer.parseInt(createFileSize.toString());
		return qtyPerFile;
	}

	private void sortSubList(int[] subList, int beg, int end) {
		// use quick sort (for a list of ten this will have a lot of overhead,
		// however
		// when dealing with larger list the quick sort will be more efficient)
		// as quick sort on average is n log n and worst case n2 (which is when
		// it is already sorted)
		// also quick sort does not involved many swaps and are therefore quick

		if (beg < end) {
			int middle = splitList(subList, beg, end);
			sortSubList(subList, beg, middle - 1);
			sortSubList(subList, middle + 1, end);
		}

	}

	private int splitList(int[] subList, int beg, int end) {
		int left = beg;
		int right = end;
		// choosing a pivot from the middle helps reduce worst case
		int mid = ((left + right) / 2);
		int pivot = subList[mid];
		// int pivot = subList[left];
		while (left < right) {

			while (subList[right] > pivot) {
				right--;
			}
			while (subList[left] <= pivot && left < right) {
				left++;
			}
			if (left < right) {
				int holdLeft = subList[left];
				subList[left] = subList[right];
				subList[right] = holdLeft;

			}
		}

		subList[beg] = subList[right];
		subList[right] = pivot;
		return right;

	}

	private void writeOutSubList(int[] subList, int size, int fileNum)
			throws FileNotFoundException {
		StringBuilder fileNameBuilder = new StringBuilder();
		fileNameBuilder.append("sortedFile");
		fileNameBuilder.append(fileNum);
		fileNameBuilder.append(".txt");
		PrintWriter writeOut = new PrintWriter(new File(
				fileNameBuilder.toString()));
		for (int i = 0; i < size - 1; i++) {
			writeOut.printf("%d ", subList[i]);
		}
		writeOut.close();

	}

	private void mergeSortSubLists(int numOfFiles) throws IOException {
		// System.out.println("still needs to be merged");
		String currentFileName;
		String mergeWithName;
		File alreadyMerged = new File("FullySorted.txt");
		int oneValue = 0;
		int twoValue = 0;
		Scanner one = null, two = null;

		// keep merging untill no files left
		for (int i = 1; i < numOfFiles; i++) {
			File temp = new File("temp.txt");
			PrintWriter out = new PrintWriter(temp);
			if (i == 1) { // on first round merge files one and two into merged
				currentFileName = "sortedFile" + i + ".txt";
				mergeWithName = "sortedFile" + (i + 1) + ".txt";

				one = new Scanner(new FileReader(currentFileName));
				two = new Scanner(new FileReader(mergeWithName));
			} else {
				one = new Scanner(new FileReader(alreadyMerged));
				two = new Scanner(new FileReader("sortedFile" + (i + 1)
						+ ".txt"));
			}
			if (one.hasNext() && two.hasNext()) {
				// this is okay since each file must have more than 2
				oneValue = one.nextInt();
				twoValue = two.nextInt();
			}
			while (one.hasNext() && two.hasNext()) {
				// write smaller one to file
				if (oneValue < twoValue) {
					// write out one
					out.printf("%d ", oneValue);
					oneValue = one.nextInt();

				} else if (oneValue == twoValue) {
					// write out both and get next for both if has next
					out.printf("%d ", oneValue);
					out.printf("%d ", twoValue);

					oneValue = one.nextInt();
					twoValue = two.nextInt();

				} else if (oneValue > twoValue) {
					// write out tw0
					out.printf("%d ", twoValue);

					twoValue = two.nextInt();

				}

			}
			// if one file still has any left write out the whole thing
			// last number from each file is not written out yet
			boolean writen = false;
			if(one.hasNext()){
			while (one.hasNext()) {
				oneValue = one.nextInt();
				if(oneValue < twoValue)
				out.printf("%d ", oneValue);
				else {
					out.printf("%d ", twoValue);
					writen = true;
				}
					

			}
			out.printf("%d ", oneValue);
			if( !writen){
				out.printf("%d ", twoValue);
			}
			}
			if(two.hasNext()){
			while (two.hasNext()) {
				twoValue = two.nextInt();
				if(oneValue < twoValue)
				out.printf("%d ", twoValue);
				else{
					out.printf("%d ", oneValue);
					writen = true;
				}

			}
			
			out.printf("%d ", twoValue);
			if(!writen){
				out.printf("%d ", oneValue);
			}
			}
			
			

			out.close();

			// reset two files
			// rename temp to already merged
			PrintWriter write = new PrintWriter(alreadyMerged);
			Scanner tempCopy = new Scanner(new FileReader(temp));
			while (tempCopy.hasNext()) {
				write.printf("%d ", tempCopy.nextInt());
			}

			write.close();

		}
	}

}