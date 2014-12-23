package org.subscribeClient;

import java.util.Scanner;

import org.subscribe.ISubscribe;
import org.subscribe.ISubscribeService;
import org.subscribe.IUnsubscribe;
import org.subscribe.IUnsubscribeService;
import org.subscribe.Info;

public class SubscribeClient {

	public static void main(String[] args) {

		
		menu();

	}

	public static void menu() {

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
			seeStats();
		}

	}

	public static void subscribe(){
		
		String regions [] = {"U.S.","Asia","Europe","Latin America","Middle East","Africa","World","World Sport"}; 
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

	public static void addNews() {
		

	}

	public static void seeStats() {

	}

}
