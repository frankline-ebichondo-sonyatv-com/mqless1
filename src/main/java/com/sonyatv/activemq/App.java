package com.sonyatv.activemq;
import com.sonyatv.activemq.consumer.TopicConsumer;
import com.sonyatv.activemq.producer.TopicProducer;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Thread topicConsumer_Thread = new Thread(new TopicConsumer());
    	topicConsumer_Thread.start();
    	
    	Thread topicProducer_Thread = new Thread(new TopicProducer());
    	topicProducer_Thread.start();
        System.out.println( "Hello World!" );
    }
}
