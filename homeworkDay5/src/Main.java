import business.concretes.UserCheckManager;
import business.concretes.UserManager;
import core.concretes.EmailManager;
import core.concretes.GoogleAuthManagerAdapter;
import dataAccess.concretes.HibernateUserDao;
import entities.concretes.User;

public class Main {

	public static void main(String[] args) {
		User user1 = new User(1, "Ertuğrul Efe", "Kaya", "efekaya2334@gmail.com", "12345eek");
		User user2 = new User(2, "Zeynep", "Kaya", "zynpkaya@gmail.com", "54321zynp");
		User user3 = new User(3, "Efe", "Kaya", "efekaya1905@gmail.com", "1233135");
		User user4 = new User(4, "Efe", "Kaya", "efekaya1905@gmail.com", "123546eek");
		User user5 = new User();

		System.out.println(
				"------------------------------------------------------------------------------------------------------");

		UserManager userManager1 = new UserManager(new HibernateUserDao(), new UserCheckManager(new EmailManager()));
		userManager1.register(user1);
		userManager1.login(user1.getEmail(), user1.getPassword());

		System.out.println(
				"------------------------------------------------------------------------------------------------------");

		userManager1.register(user2);
		userManager1.login(user2.getEmail(), user2.getPassword());

		System.out.println(
				"------------------------------------------------------------------------------------------------------");

		userManager1.register(user3);
		userManager1.login(user3.getEmail(), user3.getPassword());

		System.out.println(
				"------------------------------------------------------------------------------------------------------");

		userManager1.register(user4);
		userManager1.login(user4.getEmail(), user4.getPassword());
		userManager1.delete(user4);

		System.out.println(
				"------------------------------------------------------------------------------------------------------");

		UserManager userManager2 = new UserManager(new HibernateUserDao(),
		new UserCheckManager(new GoogleAuthManagerAdapter(), new EmailManager()));
		userManager2.register(user5);
		userManager2.login(user5.getEmail(), user5.getPassword());

	}
}