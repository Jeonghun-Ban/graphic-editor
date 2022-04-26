package global;

public enum Message {
  NEW_FILE_DIALOG("새 파일", "새 파일을 만드시겠습니까?"),
  SAVE_FILE_DIALOG("파일 저장", "변경 사항을 저장하시겠습니까?"),
  QUIT_DIALOG("종료", "종료하시겠습니까?");

  private final String title;
  private final String message;

  Message(String title, String message) {
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
