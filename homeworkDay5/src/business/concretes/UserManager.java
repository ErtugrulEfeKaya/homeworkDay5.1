package business.concretes;

import business.abstracts.UserCheckService;
import business.abstracts.UserService;
import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class UserManager implements UserService {

	private final UserDao userDao;
	private final UserCheckService userCheckService;

	public UserManager(UserDao userDao, UserCheckService userCheckService) {
		this.userDao = userDao;
		this.userCheckService = userCheckService;

	}

	@Override
	public void register(User user) {
		if (userCheckService.isValidUser(user, userDao)) {
			userDao.add(user);
			System.out.println("Kayýt baþarýlý");
			return;
		}
		System.out.println("Kayýt baþarýsýz");
	}

	@Override
	public void login(String email, String password) {
		if (userCheckService.isCorrectLoginInput(email, password, userDao)) {
			System.out.println("Kullanýcý giriþ yaptý");
			return;
		}
		System.out.println("Giriþ baþarýsýz oldu.");
	}

	@Override
	public void update(User user) {
		if (userCheckService.isValidUser(user, userDao)) {
			userDao.update(user);
			System.out.println("Güncelleme baþarýlý oldu");
			return;
		}
		System.out.println("Güncelleme baþarýsýz oldu");
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
		System.out.println("Silme iþlemi baþarýlý oldu ");
	}
}