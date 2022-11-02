package com.gdu.app1.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
	@Configuration
	안녕. 난 Bean을 만드는 Java 파일이야.
	Spring Bean Configuration File하고 하는 일이 같지.
*/

// xml을 처리하는 역할 수행
@Configuration
public class SpringBeanConfig {

	// 메소드 하나당 Bean 하나를 맡아서 생성한다.
	
	/*
		@Bean
		안녕. 난 Bean을 만드는 메소드야.
		메소드 이름이 Bean의 이름(id)이고,
		반환타입이 Bean의 타입(class)이야.
	*/
	
	/*
	 [ 1. 메소드에 이름(id)를 주는 방법 ]
	 	<bean id="song1" class="Song">
	 		<property name="title" value="제목1"/>
	 		<property name="genre" value="장르1"/>
	 	</bean>
	 */
	
	@Bean
	public Song song1() {
		Song song = new Song();
		song.setTitle("����Ʈ����");
		song.setGenre("�ε�");
		return song;
	}
	
	/*
	 [ 2. bean에 직접 이름(name)을 주는 방법 ]
	 	<bean id="song2" class="Song">
	 		<property name="title" value="제목2"/>
	 		<property name="genre" value="장르2"/>
	 	</bean>
	 */
	
	@Bean(name="song2")		  // @Bean에 name값으 지정하면 id로 사용된다..
	public Song asdfghjkl() { // 이름(id)을 Bean에 줘버리면 메소드 이름은 아무거나 적어도된다. 
		Song song = new Song();
		song.setTitle("�Ҳ�");
		song.setGenre("����");
		return song;
	}
	
	/*
	 [ 3. 생성자에 값을 주는 방법 ]
	 	<bean id="song3" class="Song">
	 		<constructor-arg value="제목3"/>
	 		<constructor-arg value="장르3"/>
	 	</bean>
	 */
	@Bean
	public Song song3() {
		return new Song("�츮���λ�������¸���", "����");
	}
	
	
	// Question.
	// song1을 가지는 singer1을 만들어 보자.
	// setter injection
	@Bean
	public Singer singer1() {
		Singer singer = new Singer();
		singer.setName("����");
		singer.setSong(song1());
		return singer;
	}
	
	
	// song2를 가지는 singer2를 name값으로 만들어 보자.
	// setter injection
	@Bean(name="singer2")
	public Singer 한요한() {
		Singer singer = new Singer();
		singer.setName("한요한");
		singer.setSong(asdfghjkl());
		return singer;
	}
	
	// song3을 가지는 singer3을 만들어보자.
	// constructor injection
	@Bean
	public Singer singer3() {
		return new Singer("기리보이", song3());
	}
	
}
