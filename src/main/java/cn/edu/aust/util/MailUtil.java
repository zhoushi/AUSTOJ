package cn.edu.aust.util;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件的工具类
 */
public class MailUtil {

    private static String HOST;
    private static String PROTOCOL;
    private static String PORT;
    private static String USER;//发件人账号
    private static String PWD;//发件人密码

    static{
        HOST = PropertiesUtil.getProperty("mail.host");
        PROTOCOL = PropertiesUtil.getProperty("mail.protocol");
        PORT = PropertiesUtil.getProperty("mail.port");
        USER = PropertiesUtil.getProperty("mail.user");
        PWD = PropertiesUtil.getProperty("mail.pwd");
    }

    /**
     * 1.获取session
     * @return
     */
    public static Session getSession(){
        //1.1构造一个保存用户名密码的Authenticator类
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER,PWD);
            }
        };
        Properties props = new Properties();
        props.put("mail.smtp.host",HOST);
        props.put("mail.store.protocol",PROTOCOL);
        props.put("mail.smtp.port",PORT);
        props.put("mail.smtp.auth",true);
        return Session.getDefaultInstance(props,authenticator);
    }

    /**
     * 2.发送不带附件的邮件
     * @param toEmail
     * @param content
     */
    public static void send(String toEmail,String content) throws MessagingException {
        Session session = getSession();
        logger.debug("准备发送邮件给"+toEmail);
        //2.1构造message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(USER));
        msg.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
        msg.setSubject("账号激活邮件");
        msg.setSentDate(new Date());
        msg.setContent(content,"text/html;charset=utf-8");
        //2.2发送邮件,因为发送比较慢,放入一个线程中
        Thread t = new Thread(()->{
            try {
                Transport.send(msg);
                logger.info("成功发送邮件"+toEmail);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        t.start();

    }

    private static Logger logger = Logger.getLogger(MailUtil.class);
}
