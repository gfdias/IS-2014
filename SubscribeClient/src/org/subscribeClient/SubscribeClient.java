package org.subscribeClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.subscribe.IAddNews;
import org.subscribe.IAddNewsService;
import org.subscribe.ISubscribe;
import org.subscribe.ISubscribeService;
import org.subscribe.IUnsubscribe;
import org.subscribe.IUnsubscribeService;
import org.subscribe.Info;

public class SubscribeClient {

	public static void main(String[] args) throws IOException {

		menu();

	}

	public static void menu() throws IOException {

		System.out.println("IS NEWS");
		System.out.println();
		System.out.println("1 - Subscribe");
		System.out.println("2 - Unsubscribe");
		System.out.println("3 - Add News");
		System.out.println("4 - See stats");
		System.out.print("Option: ");

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		int option = sc.nextInt();

		switch (option) {

		case 1:
			subscribe();
		case 2:
			unsubscribe();
		case 3:
			addNews();
		}

	}

	public static void subscribe() {

		String regions[] = { "U.S.", "Asia", "Europe", "Latin America",
				"Middle East", "Africa", "World", "World Sport" };
		String email;
		int type;
		String region;

		Scanner sc = new Scanner(System.in);

		System.out.print("Email: ");
		email = sc.nextLine();

		System.out.println("0 - Digest");
		System.out.println("1 - Non-Digest");
		System.out.print("Option: ");
		type = sc.nextInt();
		sc.nextLine();

		System.out.println();
		System.out.println("0 - US");
		System.out.println("1 - Asia");
		System.out.println("2 - Europe");
		System.out.println("3 - Latin America");
		System.out.println("4 - Middle East");
		System.out.println("5 - Africa");
		System.out.println("6 - World");
		System.out.println("7 - World Sport");
		System.out.print("Region: ");
		region = regions[sc.nextInt()];
		sc.nextLine();

		ISubscribeService service = new ISubscribeService();
		ISubscribe subscribe = service.getISubscribePort();
		Info info = subscribe.subscribeUser(email, type, region);
	}

	public static void unsubscribe() {

		String email;
		Scanner sc = new Scanner(System.in);

		System.out.print("Email: ");
		email = sc.nextLine();

		IUnsubscribeService service = new IUnsubscribeService();
		IUnsubscribe unsubscribe = service.getIUnsubscribePort();

		System.out.println(unsubscribe.unsubscribeUser(email));
	}

	public static void addNews() throws IOException {
		
		IAddNewsService service = new IAddNewsService();
		IAddNews add = service.getIAddNewsPort();
		

		File folder = new File("Save/");
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (!listOfFiles[i].getName().endsWith(".xml"))
					continue;
				
				String file = "Save/" + listOfFiles[i].getName();
				System.out.println("file");
				String xmlString = readFile(file);
				System.out.println("Imported " + listOfFiles[i].getName());
				
				System.out.println("oi");
				String t = add.addNews(xmlString);
			}
		}
	}
	

	public static String readFile(String path) throws IOException{
		StringBuilder sb = new StringBuilder();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String s;

			while (( s = br.readLine()) != null) {
				sb.append(s);
			}
		}

		return sb.toString();
	}

	public static void seeStats() {

	}

}
