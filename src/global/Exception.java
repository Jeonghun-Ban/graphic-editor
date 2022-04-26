package global;

public enum Exception {

  PRINT_NOT_AVAILABLE("프린터 에러", "프린터에서 예상치 못한 에러가 발생하였습니다. 프린터 연결을 확인해주세요."),
  FILE_STORE_SAVE_ERROR("파일 저장 에러", "파일을 저장하는 과정에서 에러가 발생하였습니다."),
  FILE_STORE_LOAD_ERROR("파일 열기 에러", "파일을 가져오는 과정에서 에러가 발생하였습니다.");

  private final String title;
  private final String message;

  Exception(String title, String message) {
    this.title = title;
    this.message = message;
  }

  public String toString() {
    return this.message;
  }

  public String getTitle() {
    return this.title;
  }

  public String getMessage() {
    return this.message;
  }
}
