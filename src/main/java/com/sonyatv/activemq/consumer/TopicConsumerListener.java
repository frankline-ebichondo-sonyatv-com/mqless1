package com.sonyatv.activemq.consumer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumerListener implements Runnable, MessageListener{
	Session session;
	MessageConsumer consumer;
	Connection connection;

	public void run(){
		try{
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			connection = connectionFactory.createConnection();
			connection.start();
			
			session = connection.createSession(false,  Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue("TEST.FOO");
			
			consumer = session.createConsumer(destination);
			
			consumer.setMessageListener(this);
						
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void onMessage(Message message){
		
		try{
			if(message instanceof TextMessage){
				TextMessage textMessage = (TextMessage)message;
				String text = textMessage.getText();
				System.out.println("Received: " + text);
			}else{
				System.out.println("Received: " + message);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				consumer.close();
				session.close();
				connection.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
