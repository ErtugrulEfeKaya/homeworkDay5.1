package business.concretes;

import java.util.regex.Pattern;

import business.abstracts.UserCheckService;
import core.abstracts.AuthService;
import core.abstracts.EmailService;
import dataAccess.abstracts.UserDao;
import entities.concretes.User;

public class UserCheckManager implements UserCheckService {

	private AuthService authService;
	private final EmailService emailService;

	public UserCheckManager(EmailService emailService) {
		this.emailService = emailService;
	}

	public UserCheckManager(AuthService authService, EmailService emailService) {
		this.authService = authService;
		this.emailService = emailService;
	}

	@Override
	public boolean isValidFirstName(String firstName) {
		if (firstName.length() >= 2)
			return true;
		System.out.println("İsim 2 karakterden kısa olamaz!");
		return false;
	}

	@Override
	public boolean isValidLastName(String lastName) {
		if (lastName.length() >= 2)
			return true;
		System.out.println("Soyadı 2 karakterden kısa olamaz!");
		return false;
	}

	@Override
	public boolean isValidPassword(String password) {
		if (password.length() >= 6)
			return true;
		System.out.println("Şifre 6 karakterden kısa olamaz!");
		return false;
	}

	@Override
	public boolean isValidEmailFormat(String email) {
		String emailRegex = "^\\w+(\\.\\w+)*@[a-zA-Z]+(\\.\\w{2,6})+$";
		Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
		if (email == null) {
			System.out.println("Hatalı Mail Formatı!");
			return false;
		} else if (!pattern.matcher(email).matches())
			System.out.println("Hatalı Mail Formatı!");
		return true;
	}

	@Override
	public boolean isUsedEmail(String email, UserDao userDao) {
		if (userDao.getByEmail(email) != null) {
			System.out.println("Bu mail adresi daha önce kullanılmış!");
			return true;
		}
		return false;
	}

	@Override
	public boolean isValidUser(User user, UserDao userDao) {
		if (authService != null)
			return authService.isValidUser();
		if (!isValidFirstName(user.getFirstName()))
			return false;
		else if (!isValidLastName(user.getLastName()))
			return false;
		else if (!isValidEmailFormat(user.getEmail()))
			return false;
		else if (!isValidPassword(user.getPassword()))
			return false;
		else if (isUsedEmail(user.getEmail(), userDao))
			return false;
		emailService.sendVerificationMail(user.getEmail());
		System.out.println("Doğrulandı+");
		return true;
	}

	@Override
	public boolean isCorrectLoginInput(String email, String password, UserDao userDao) {
		if (authService != null)
			return authService.isValidUser();
		User user = userDao.getByEmail(email);
		if (user == null) {
			System.out.println("Hatalı e-mail!");
			return false;
		} else if (!user.getPassword().equals(password)) {
			System.out.println("Hatalı şifre!");
			return false;
		}
		return true;
	}
}