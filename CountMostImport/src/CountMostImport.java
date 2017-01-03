import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.Map.Entry;


import java.io.BufferedReader;
import java.io.File;

public class CountMostImport {
	static Map<String, Integer> class_count = new HashMap<String, Integer>();
	static PrintStream ps;

	/**
	 * 读取某个文件夹下的所有文件
	 */
	public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
		try {

			File file = new File(filepath);
			if (!file.isDirectory()) {
				if (filepath.endsWith(".java")) {
					ps.println("   文件      " + file.getPath());
					checkFile(filepath, class_count);
				}

			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					readfile(filepath + "/" + filelist[i]);

				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return true;
	}

	public static void checkFile(String filePath, Map<String, Integer> class_count) {
		File file = new File(filePath);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				if (tempString.contains("{")) {
					break;
				}
				if (tempString.startsWith("import")) {
					String className = tempString.substring(tempString.indexOf("import") + 7,
							tempString.lastIndexOf(";"));
					ps.println(className);
					Integer count=class_count.get(className);
					if (count == null) {
						class_count.put(className, 1);
					} else {
						class_count.put(className, count + 1);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

	public static void main(String[] args) {
		try {
			File file = new File("./result");
			ps = new PrintStream(new FileOutputStream(file));
			ps.println("开始");

			System.out.print("请输入路径:");
			Scanner scanner=new Scanner(System.in);
			readfile(scanner.nextLine());

			List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(class_count.entrySet());

			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
				// 降序排序
				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					 //return o1.getValue().compareTo(o2.getValue());
					return o2.getValue().compareTo(o1.getValue());
				}
			});
			ps.println("统计结果： ");
			int count=0;
			for (Map.Entry<String, Integer> entry : list) {
				if (count>9) {
					break;
				}
				System.out.println(entry.getKey() + " : " + entry.getValue());
				ps.println(entry.getKey() + " : " + entry.getValue());
				count++;
			}
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}
		System.out.println("ok");
	}

}