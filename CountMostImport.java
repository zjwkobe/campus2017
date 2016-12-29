package countMostImport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CountMostImport {
	static List<File> lfile = new ArrayList<File>(); // 存放文件
	static List<String> str = new ArrayList<String>(); // 存放匹配import java.*的字符串

	public static void main(String[] args) throws IOException {
		CountMostImport countMostImport = new CountMostImport();
		Map<String, Integer> count = new HashMap<String, Integer>(); // 存放截取的字符串（import的类）
		String file_path = "D:\\eclipse\\project\\src";
		File file = new File(file_path);
		countMostImport.listDirectory(file);
		countMostImport.count(lfile);
		count = countMostImport.cutLine(str);
		countMostImport.sortByValue(count);
		// System.out.println(str.size());
		// for(String f : str) {
		// System.out.println(f);
		// }
//		Set<Entry<String, Integer>> entryset = count.entrySet();
//		for (Entry<String, Integer> entry : entryset) {
//			System.out.print(entry.getKey() + " " + " ");
//			System.out.print(":" + entry.getValue());
//			System.out.println();
//		}

	}

	// 循环遍历src目录下的子目录，java文件路径放到集合里
	public void listDirectory(File dir) {
		if (!dir.exists()) {
			throw new IllegalArgumentException("目录" + dir + "不存在！");
		}
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException(dir + "不是目录！");
		}
		File[] files = dir.listFiles();
		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isDirectory()) {
					listDirectory(file);
				} else {
					lfile.add(file);
				}
			}
		}

	}

	public void count(List<File> lfile) throws IOException {
		for (File file : lfile) {
			FileInputStream in = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.contains("import") && (!(line.contains("*")))) {
					String[] line1 = line.split(" ");
					String[] line2 = line1[1].split(";");
					str.add(line2[0]);

				}
			}
		}
	}

	public Map<String, Integer> cutLine(List<String> string) {
		Map<String, Integer> count = new HashMap<String, Integer>(); // 存放截取的字符串（import的类）
		Integer init = new Integer(1);
		for (String str : string) {
			if (count.containsKey(str)) {
				int key = count.get(str);
				key++;
				count.put(str, key);
			} else {
				count.put(str, init);
			}
		}
		return count;
	}

	public void sortByValue(Map<String, Integer> map) {
		// 将Map转为List
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return (o2.getValue() - o1.getValue());
			}
		});
		System.out.println("被引用最多的10个类：");
		for (int i = 0; i < 10; i++) {
			System.out.println(list.get(i).getKey()+"="+list.get(i).getValue());
		}
		
	}

}
