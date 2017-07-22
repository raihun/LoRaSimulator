public class Node {

  private int id = -1;
  private int x = 0;
  private int y = 0;

  // コンストラクタ
  public Node() {}
  public Node(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public Node(int x, int y, int id) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.name = String.valueOf(this.id);
  }

  // ノードID
  public void setId(int id) {
    this.id = id;
  }
  public int getId() {
    return this.id;
  }

  // ノード名
  private String name = String.valueOf(this.id);
  public void setName(String name) {
    this.name = name;
  }
  public String getName() {
    return this.name;
  }

  // 座標
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public int[] getPosition() {
    int[] position = {this.x, this.y};
    return position;
  }

  // 現在時刻 (ローカルタイム)
  private int nowTime = 0;
  public void setNowtime(int nowTime) {
    this.nowTime = nowTime;
  }
  public int getNowtime() {
    return this.nowTime;
  }
  public void forwardNowtime() {
    this.nowTime += 3;
    this.nowTime %= 3600;
  }

  // 稼働時間
  private int operationTime = 300;
  public void setOperationTime(int operationTime) {
    this.operationTime = operationTime;
  }
  public int getOperationTime() {
    return this.operationTime;
  }
  public boolean isOperation() {
    return (this.getNowtime() < this.operationTime);
  }

  // GUI用
  private int cnt = 0;
  public int getCount() {
    this.cnt += 10;
    if(this.cnt > 600) this.cnt = 0;
    return (this.cnt < 200 ? this.cnt : 0);
  }

}
