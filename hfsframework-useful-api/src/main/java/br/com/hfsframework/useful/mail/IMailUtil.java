package br.com.hfsframework.useful.mail;

import javax.mail.MessagingException;


public interface IMailUtil {

	public void sendSimpleMessage(String to, String subject, String text);

	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment)
			throws MessagingException;

}
