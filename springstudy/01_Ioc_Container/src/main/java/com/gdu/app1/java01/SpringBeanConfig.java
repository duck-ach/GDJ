package com.gdu.app1.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
	@Configuration
	�ȳ�. �� Bean�� ����� Java �����̾�.
	Spring Bean Configuration File�ϰ� �ϴ� ���� ����.
*/

// xml�� ó���ϴ� ���� ����
@Configuration
public class SpringBeanConfig {

	// �޼ҵ� �ϳ��� Bean �ϳ��� �þƼ� �����Ѵ�.
	
	/*
		@Bean
		�ȳ�. �� Bean�� ����� �޼ҵ��.
		�޼ҵ� �̸��� Bean�� �̸�(id)�̰�,
		��ȯŸ���� Bean�� Ÿ��(class)�̾�.
	*/
	
	/*
	 [ 1. �޼ҵ忡 �̸�(id)�� �ִ� ��� ]
	 	<bean id="song1" class="Song">
	 		<property name="title" value="����1"/>
	 		<property name="genre" value="�帣1"/>
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
	 [ 2. bean�� ���� �̸�(name)�� �ִ� ��� ]
	 	<bean id="song2" class="Song">
	 		<property name="title" value="����2"/>
	 		<property name="genre" value="�帣2"/>
	 	</bean>
	 */
	
	@Bean(name="song2")		  // @Bean�� name���� �����ϸ� id�� ���ȴ�.
	public Song asdfghjkl() { // �̸�(id)�� Bean�� ������� �޼ҵ� �̸��� �ƹ��ų� ����ȴ�. 
		Song song = new Song();
		song.setTitle("�Ҳ�");
		song.setGenre("����");
		return song;
	}
	
	/*
	 [ 3. �����ڿ� ���� �ִ� ��� ]
	 	<bean id="song3" class="Song">
	 		<constructor-arg value="����3"/>
	 		<constructor-arg value="�帣3"/>
	 	</bean>
	 */
	@Bean
	public Song song3() {
		return new Song("�츮���λ�������¸���", "����");
	}
	
	
	// Question.
	// song1�� ������ singer1�� ����� ����.
	// setter injection
	@Bean
	public Singer singer1() {
		Singer singer = new Singer();
		singer.setName("����");
		singer.setSong(song1());
		return singer;
	}
	
	
	// song2�� ������ singer2�� name������ ����� ����.
	// setter injection
	@Bean(name="singer2")
	public Singer �ѿ���() {
		Singer singer = new Singer();
		singer.setName("�ѿ���");
		singer.setSong(asdfghjkl());
		return singer;
	}
	
	// song3�� ������ singer3�� ������.
	// constructor injection
	@Bean
	public Singer singer3() {
		return new Singer("�⸮����", song3());
	}
	
}
