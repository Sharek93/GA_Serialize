import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		SerializePerson();
		MySQLConnector.Connect();		
	}

	private static void SerializePerson() {
		Person person = new Person(1, "Jan", "Kowalski", 44, true);

		System.out.println("Serializacja");
		try {
			FileOutputStream p1 = new FileOutputStream("osoba.txt");
			ObjectOutputStream s = new ObjectOutputStream(p1);
			s.writeObject(person);
			s.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		System.out.println("Deserializacja");

		try {
			FileInputStream p2 = new FileInputStream("osoba.txt");
			ObjectInputStream s = new ObjectInputStream(p2);
			Person importedPerson = (Person) s.readObject();
			s.close();
			System.out.println(importedPerson);
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		}
	}
}
