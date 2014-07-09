package com.sonyatv.activemq.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumer implements Runnable{

	public void run(){
		try{
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false,  Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue("TEST.FOO");
			
			MessageConsumer consumer = session.createConsumer(destination);
			
			Message message = consumer.receive(10000);
			
			if(message instanceof TextMessage){
				TextMessage textMessage = (TextMessage)message;
				String text = textMessage.getText();
				System.out.println("Received: " + text);
			}else{
				System.out.println("Received: " + message);
			}
			
			consumer.close();
			session.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
