package core.concretes;

import core.abstracts.EmailService;

public class EmailManager implements EmailService {

	@Override
	public void sendVerificationMail(String email) {
		System.out.println("Do�rulama maili �u mail adresine yolland�: " + email);
	}

}
