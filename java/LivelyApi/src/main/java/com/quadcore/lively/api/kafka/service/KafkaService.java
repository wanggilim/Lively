package com.quadcore.lively.api.kafka.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.clients.producer.Callback;
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.clients.producer.RecordMetadata;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KafkaStreams;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.KTable;
//import org.apache.kafka.streams.kstream.Predicate;
//import org.apache.kafka.streams.kstream.Produced;

//import com.quadcore.lively.api.twitter.config.TwitterConfiguration;
import com.quadcore.lively.api.util.FileReaderWriter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class KafkaService {
	
	public KafkaService() {
		
	}
	
//	public void send() {
//		final Properties props = new Properties();
//		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "twitter-streams-input");
//		props.put("bootstrap.servers", "localhost:9092");//livelyathome.iptime.org:12909
//		props.put("security.protocol", "PLAINTEXT");
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");   // serialize 설정
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // serialize 설정
//        props.put("serializer.encoding", "UTF-8");
//        props.put("deserializer.encoding", "UTF-8");
//        
//
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        
//        System.out.println("트위터로부터 수집을 시작합니다.");
//        
//        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
//        
//        StatusListener listener = new StatusListener(){
//
//	        public void onStatus(Status status) {
//        		// 이모티콘 필터링
//        		final Pattern pattern = Pattern.compile(
//        	            "[\\uD83C-\\uDBFF\\uDC00-\\uDFFF]+"
//        	            + "|[\ud83c\udc00-\ud83c\udfff]"
//        	            + "|[\ud83d\udc00-\ud83d\udfff]"
//        	            + "|[\u2600-\u27ff]"
//        	            + "|\\uD83D[\\uDC00-\\uDFFF]"
//        	            + "|\\uD83C[\\uDC00-\\uDFFF]"
//        	            + "|\\uFFFD",
//        	            Pattern.UNICODE_CASE |
//        	            Pattern.CANON_EQ |
//        	            Pattern.CASE_INSENSITIVE
//        		);
//        		Matcher matcher = pattern.matcher(status.getText());
//	        	// 유저정보        		
//        		String screenName = status.getUser().getScreenName();
//        		// 메시지        		
//        		String input = matcher.replaceAll(status.getText());
//        		
//        		producer.send(new ProducerRecord<String, String>("twitter-streams-input", screenName, input),
//    				new Callback() {
//						@Override
//						public void onCompletion(RecordMetadata metadata, Exception exception) {
//							if (exception == null) {
//								System.out.println(screenName + ": " + input + " ==> " + metadata.topic());
//							}
//						}
//					}
//        		);
//        		
//	        }
//	        
//	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
//	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
//	        public void onException(Exception ex) {
//	            ex.printStackTrace();
//	        }
//			@Override
//			public void onScrubGeo(long userId, long upToStatusId) {
//				// TODO Auto-generated method stub
//				
//			}
//			@Override
//			public void onStallWarning(StallWarning warning) {
//				// TODO Auto-generated method stub
//				
//			}
//	    };
//	    
//	    TwitterStream twitterStream = new TwitterStreamFactory(TwitterConfiguration.getConfig()).getInstance();
//	    twitterStream.addListener(listener);
//	    twitterStream.sample("en");
//	    
//	    final CountDownLatch latch = new CountDownLatch(1);
//	    Runtime.getRuntime().addShutdownHook(new Thread("streams-input-shutdown-hook") {
//            @Override
//            public void run() {
//            	producer.abortTransaction();
//            	producer.close();
//            }
//        });
//	    
//	    try {
//            latch.await();
//        } catch (final Throwable e) {
//            System.exit(1);
//        }
//        System.exit(0);
//	    
//	}
//	
//	public void wordcount() { 
//		final Properties props = new Properties();
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "twitter-streams-wordcount");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put("serializer.encoding", "UTF-8");
//        props.put("deserializer.encoding", "UTF-8");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        
//        
//        
//        // 불용어 지정
//        FileReaderWriter reader = new FileReaderWriter();
//        String stopwords = reader.readAllToString("/Users/wgl/english_filter");
//        String allowwords = reader.readAllToString("/Users/wgl/english_filternot");
//        
//        final StreamsBuilder builder = new StreamsBuilder();
//        final KStream<String, String> source = builder.stream("twitter-streams-input");
//        final KTable<String, Long> counts = source
//            //.flatMapValues(value -> Arrays.asList(value.toLowerCase().split(" |\n|\t|\\W+")))
//            .flatMapValues(value -> Arrays.asList(value.toLowerCase().split(" |\n|\t")))
//            .filter(new Predicate<String, String>() {
//            	@Override
//            	public boolean test(String key, String value) {
//            		
//            		if (!value.matches("\\W+") || (stopwords.contains(value) && !allowwords.contains(value))) {
//            			System.out.println("필터링 : " + value);
//						return false;
//					}
//            		return true;
//            	}
//			})
//            .groupBy((key, value) -> value)
//            .count();
//        
//        counts.toStream(((key, value) -> key))
//        .to("twitter-streams-wordcount", Produced.with(Serdes.String(), Serdes.Long()));
//        
//        
//        
//        /****
//         * 
//         *  콘솔에서 실행하려면
//         *  kafka-console-consumer --topic twitter-streams-wordcount \
//         *  --from-beginning --bootstrap-server localhost:9092 \
//         *  --property print.key=true \
//         *  --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
//         *  
//         *  kafka-console-consumer --topic twitter-streams-wordcount --from-beginning --bootstrap-server localhost:9092 --property print.key=true --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
//         */
//        final KafkaStreams streams = new KafkaStreams(builder.build(), props);
//        final CountDownLatch latch = new CountDownLatch(1);
//
//        
//        ////////////////////////
//        
//        
//        // 시간대별로 자동저장
//        KafkaConsumer<String, Long> fileConsumer = new KafkaConsumer<>(props);
//        ConsumerRecords<String, Long> records = fileConsumer.poll(Duration.ofMillis(10000));
//        
//        for (ConsumerRecord<String, Long> record : records) {
//        	System.out.println(record.topic() + ":\n" + record.key() + ", " + record.value());
//        	
//        	// 파일 불러오기 없으면 새로 저장	
//        	
//        	// key가 파일에 포함된다면 -> key는 두고 value만 교체
//        	
//        	// 저장 확인
//		}
//		
//		////////////////////////
//		
//        
//        Runtime.getRuntime().addShutdownHook(new Thread("streams-wordcount-shutdown-hook") {
//            @Override
//            public void run() {
//            	fileConsumer.close();
//                streams.close();
//                latch.countDown();
//            }
//        });
//
//        try {
//            streams.start();
//            latch.await();
//        } catch (final Throwable e) {
//            System.exit(1);
//        }
//        System.exit(0);
//	}
	
}
