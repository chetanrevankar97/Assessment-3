import java.util.*;

public class Store {
	
	ArrayList<Item> itemAL = new ArrayList<>();
	ArrayList<Item> cart = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int option = 0;
		Scanner sc = new Scanner(System.in);
		Store s = new Store();

		boolean flag = true;
		do {
			try {
				System.out.println("\n1:Stock\t2:Sale\t3:Exit");
				option = sc.nextInt();
				switch (option) {

				case 1:
					int option2 = 0;
					do {
						System.out.println("\n1:Add Stock\t2:Update Stock\t3:Remaining Stock\t4:Exit");
						option2 = sc.nextInt();
						switch (option2) {
						case 1:
							s.add_stock();
							break;
						case 2:
							s.update_stock();
							break;
						case 3:
							s.remaining();
							break;
						case 4:
							option2 = -1;
							break;
						}

					} while (option2 != -1);
					break;

				case 2:
					int option3 = 0;
					do {
						System.out.println("\n1:Add to Cart\t2:View Invoice\t3:Exit");
						option3 = sc.nextInt();
						switch (option3) {
						case 1:
							s.add_cart();
							break;
						case 2:
							s.show_invoice();
							break;
						case 3:
							option3 = -1;
							break;
						}

					} while (option3 != -1);
					break;

				case 3:
					System.out.println("\nTHANK YOU\nExiting...\n\n");
					option = -1;
					break;
				}
			} catch (invalidException e) {
				System.err.println(e.getMessage());
			}

		} while (option != -1);
	}

	public void remaining() {
		
		System.out.println("\nREMAINING STOCK");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%10s %15s %15s %15s", "CODE", "NAME", "PRICE", "QUANTITY");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		for (Item i : itemAL) {
			System.out.format("%10d %15s %15f %15d", i.code, i.name, i.price, i.quantity);
			System.out.println();
		}
	}

	public void show_invoice() {
		System.out.println("\nINVOICE");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%10s %15s %15s %15s", "CODE", "NAME", "PRICE", "QUANTITY");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		for (Item i : cart) {
			System.out.format("%10d %15s %15f %15d", i.code, i.name, i.price, i.quantity);
			System.out.println();
		}
		System.out.println();
		float total = 0;
		for (Item i2 : cart) {
			total = total + (i2.price * i2.quantity);
		}
		System.out.println("\nTOTAL AMOUNT: Rs. " + total + "\n");
	}

	public void add_cart() throws invalidException {

		System.out.println("\nADD TO CART");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the item code to add to cart");
		int searchCode = sc.nextInt();
		int sindex = 0;
		for (int i = 0; i < itemAL.size(); i++) {
			if (searchCode == itemAL.get(i).code) {
				sindex = i;
				System.out.println("Enter the item quantity");
				int quantity1 = sc.nextInt();
				if (quantity1 > itemAL.get(sindex).quantity) {
					throw new invalidException("Insufficient quantity\n");
				} else {
					itemAL.get(sindex).quantity = itemAL.get(sindex).quantity - quantity1;
					cart.add(new Item(itemAL.get(sindex).code, itemAL.get(sindex).name, itemAL.get(sindex).price,
							quantity1));
				}
				break;
			}
			
		}
	}

	public void add_stock() throws invalidException {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nSTOCK ADD");

		System.out.println("Enter the item code");
		int code1 = sc.nextInt();
		int clength = String.valueOf(code1).length();
		if (clength < 3 || clength > 3) {
			throw new invalidException("Invalid !! Item Code should have 3 digits\n");
		}

		System.out.println("Enter the item name");
		String name1 = sc.next();
		char[] narray = name1.toCharArray();
		for (char i : narray) {
			if (Character.isDigit(i)) {
				
				throw new invalidException("Item name has numbers\n");

			}
			if (narray.length <= 1) {
				
				throw new invalidException("Item name is short");

			}
		}

		System.out.println("Enter the item price");
		float price1 = sc.nextFloat();
		System.out.println("Enter the item quantity");
		int quantity1 = sc.nextInt();
		itemAL.add(new Item(code1, name1, price1, quantity1));
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%10s %15s %15s %15s", "CODE", "NAME", "PRICE", "QUANTITY");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		for (Item i : itemAL) {
			System.out.format("%10d %15s %15f %15d", i.code, i.name, i.price, i.quantity);
			System.out.println();
		}
	}

	public void update_stock() throws invalidException {
		System.out.println("\nSTOCK UPDATE");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter item code to be updated");
		int searchCode = sc.nextInt();
		int sindex = 0;
		for (int i = 0; i < itemAL.size(); i++) {
			if (searchCode == itemAL.get(i).code) {
				sindex = i;

				itemAL.remove(sindex);

				int code1 = searchCode;
				System.out.println("Item code to update is: " + code1);
				System.out.println("Enter the item name");
				String name1 = sc.next();
				System.out.println("Enter the item price");
				float price1 = sc.nextFloat();
				System.out.println("Enter the item quantity");
				int quantity1 = sc.nextInt();
				itemAL.add(sindex, new Item(code1, name1, price1, quantity1));
				System.out.println("-----------------------------------------------------------------------------");
				System.out.printf("%10s %15s %15s %15s", "CODE", "NAME", "PRICE", "QUANTITY");
				System.out.println();
				System.out.println("-----------------------------------------------------------------------------");
				for (Item i1 : itemAL) {
					System.out.format("%10d %15s %15f %15d", i1.code, i1.name, i1.price, i1.quantity);
					System.out.println();
				}
				System.out.println();
				break;
			}
			

		}

	}

}
