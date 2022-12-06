package ex03_functional_interface.sec01;

@FunctionalInterface // 추상메소드를 1개 가지고 있는 메소드(=람다식으로 생성할 수 있는 인터페이스)
public interface MyInterface1 {
	/* 람다식을 적용할 수 있는 4가지 추상메소드가 나와있음. 뭐집어넣으면 뭐 나온다가 약속되어있음 공부할때는 그 4개 추상메소드로 공부하면 됨. */
	
	public void method();
	
}
