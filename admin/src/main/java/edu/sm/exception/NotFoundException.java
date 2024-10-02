package edu.sm.exception;

// 사용자 정의 예외 클래스
public class NotFoundException extends Exception {
  // 생성자
  public NotFoundException(String message) {
    super(message); // 부모 클래스인 Exception의 생성자 호출
  }
}
