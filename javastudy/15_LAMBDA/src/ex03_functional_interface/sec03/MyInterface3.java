package ex03_functional_interface.sec03;

@FunctionalInterface // 람다로 쓸거면 적어주는 게 안전함 (메소드가 2개작성되면 오류를 냄)
public interface MyInterface3 {
	public int method();
}
