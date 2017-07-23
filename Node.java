import java.util.ArrayList;
public class Node {

  private static NodeController nc = null;

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
    this.setName(String.valueOf(this.id));
  }

  // インスタンス
  public void setNodeController(NodeController nc) {
    this.nc = nc;
  }

  // ノードID
  public void setId(int id) {
    this.id = id;
  }
  public int getId() {
    return this.id;
  }

  // ノード名
  private String name = "";
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
    this.nowTime %= 3600; // 3600を法とする
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

  // パケット送信
  public boolean sendPacket(Packet packet) {
    // パケット情報取得
    int[] datalink = packet.getDatalink();
    int[] network = packet.getNetwork();

    // 自分宛へのパケットを破棄
    if(datalink[0] == this.id) {
      packet = null;
      return false;
    }

    // デバッグ情報
    String message = String.format("[Send:%3d] D:%3d =>%3d      N:%3d =>%3d", this.id, datalink[1], datalink[0], network[1], network[0]);
    System.out.println(message);

    // 電波が届く、周囲のノードへ送信
    ArrayList<Node> nodeList = this.nc.getNodesByDistance(this, 100.0);
    if(nodeList.size() < 1) return false;

    for(Node node : nodeList) {
      if(node == this) continue; // 自分自身への送信を防ぐ
      node.receivePacket(packet);
    }

    // 返却
    return true;
  }

  // パケット受信
  public void receivePacket(Packet packet) {
    // パケット情報取得
    int[] datalink = packet.getDatalink();
    int[] network = packet.getNetwork();

    // 自分宛以外のパケットを破棄
    if(datalink[0] != this.id) {
      packet = null;
      return;
    }

    // デバッグ情報
    String message = String.format("[Recv:%3d] D:%3d =>%3d      N:%3d =>%3d", this.id, datalink[1], datalink[0], network[1], network[0]);
    System.out.println(message);

    // タイプ別処理
    switch(packet.getType()) {
      case 0x00: // 未使用
        break;

      case 0x01: // PING
        Packet _packet = (Packet)packet.clone();
        _packet.setDatalink(datalink[1], datalink[0]);
        _packet.setNetwork(network[1], network[0]);
        _packet.setType((byte)0x02);
        this.sendPacket(_packet);
        break;

      case 0x02: // PING-ECHO

        break;
    }
    return;
  }

  // GUI用
  private int cnt = 0;
  public int getCount() {
    this.cnt += 10;
    if(this.cnt > 600) this.cnt = 0;
    return (this.cnt < 200 ? this.cnt : 0);
  }

}
