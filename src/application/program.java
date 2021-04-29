package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.print("How many employees will be registered? ");
		Integer n = sc.nextInt();
		
		List<Employee> list = new ArrayList<>();
				
		for (int i=1; i<=n; i++) {
			System.out.println();
			System.out.println("Employee #" + i + ": ");
			System.out.print("id: ");
			int id = sc.nextInt();

			while (hasId(list, id)) {
				System.out.print("id already taken. Try another one: ");
				id = sc.nextInt();
			}
			
			sc.nextLine();
			System.out.print("Name: ");
			String name = sc.nextLine();
			System.out.print("Salary: ");
			double salary = sc.nextDouble();
			Employee employee = new Employee(id, name, salary);
			list.add(employee);
		}
			
		System.out.println();
		System.out.print("Enter the employee id that will have salary increased: ");
		int id = sc.nextInt();
		
		Integer idPosition = position(list, id);
		
		while (idPosition == null) {
			System.out.print("this id doesn't exist!. Try another one: ");
			id = sc.nextInt();
			idPosition = position(list, id);
		}
		
		System.out.print("Enter the percentage: ");
		double percentage = sc.nextDouble();
		list.get(idPosition).increasePercentage(percentage);
		
		/*  //alternative version
		Employee employee = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);

		if (employee == null) {
			System.out.println("this id doesn't exist!");
		}
		else {
			System.out.print("Enter the percentage: ");
			double percentage = sc.nextDouble();
			employee.increasePercentage(percentage);
		}
		*/
		
		System.out.println();
		System.out.println("List of employees:");
		for (Employee employee : list) {
			System.out.println(employee);
		}
		
		sc.close();
		}

	public static boolean hasId(List<Employee> list,int id){
		Employee result = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return result != null;
	}
	
	public static Integer position(List<Employee> list, int id) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}
		return null;
	}
}
