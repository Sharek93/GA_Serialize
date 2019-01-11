import java.io.Serializable;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String lastName;
	private int age;
	private boolean isActive;
	
	public Person(int id, String name, String lastName, int age, boolean isActive)	{
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", lastName=" + lastName + ", age=" + age + ", isActive="
				+ isActive + "]";
	}
	
}
