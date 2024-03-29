import java.util.ArrayList;

public class NodeController {

  private ArrayList<Node> nodeList;
  private int coundId = 0;

  // コンストラクタ
  public NodeController() {
    this.nodeList = new ArrayList<Node>();
  }

  public int getCountId() {
    return this.coundId;
  }

  // ノード追加
  public void addNode(int x, int y) {
    Node node = new Node(x, y, this.coundId++);
    node.setNodeController(this);
    this.nodeList.add(node);
  }

  // ノード削除
  public boolean removeNode(int x, int y) {
    // ノード数チェック
    if(this.nodeList.size() < 1) return false;

    // 距離確認
    ArrayList<Node> removeNodeList = new ArrayList<Node>();
    for(Node node : this.nodeList) {
      int[] pos = node.getPosition();
      double distance = Math.sqrt(Math.pow(x - pos[0], 2) + Math.pow(y - pos[1], 2));
      if(distance < 5.0) removeNodeList.add(node);
    }

    // 削除リストから削除
    for(Node node : removeNodeList) {
      nodeList.remove(nodeList.indexOf(node));
    }
    return true;
  }

  // ルーティングテーブル表示
  public void routeNode(int x, int y) {
    // ノード数チェック
    if(this.nodeList.size() < 1) return;

    // 距離確認
    ArrayList<Node> selectNodeList = new ArrayList<Node>();
    for(Node node : this.nodeList) {
      int[] pos = node.getPosition();
      double distance = Math.sqrt(Math.pow(x - pos[0], 2) + Math.pow(y - pos[1], 2));
      if(distance < 5.0) selectNodeList.add(node);
    }

    // 該当ノードに指示
    for(Node node : selectNodeList) {
      node.showRouteList();
    }
  }

  // Requestパケット送信
  public void requestNode(int x, int y) {
    // ノード数チェック
    if(this.nodeList.size() < 1) return;

    // 距離確認
    ArrayList<Node> selectNodeList = new ArrayList<Node>();
    for(Node node : this.nodeList) {
      int[] pos = node.getPosition();
      double distance = Math.sqrt(Math.pow(x - pos[0], 2) + Math.pow(y - pos[1], 2));
      if(distance < 5.0) selectNodeList.add(node);
    }

    // 該当ノードがRequestパケット送信
    for(Node node : selectNodeList) {
      node.sendRequestPacket();
    }
    return;
  }

  //　ノード取得 (All)
  public ArrayList<Node> getNodes() {
    return this.nodeList;
  }

  // ノード取得 (by ID)
  public Node getNodeById(int nodeId) {
    Node result = null;

    // 範囲チェック
    if(nodeId < 0 || this.coundId < nodeId) return null;

    // 検索
    for(Node node : this.nodeList) {
      if(node.getId() == nodeId) {
        result = node;
        break;
      }
    }

    // 返却
    return result;
  }

  // ノード取得 (by Distance)
  public ArrayList<Node> getNodesByDistance(Node node, double distance) {
    ArrayList<Node> result = new ArrayList<Node>();
    for(Node _node : this.nodeList) {
      if(node == _node) continue; // 対象ノードは含めない
      if(this.getDistance(node, _node) <= distance) {
        result.add(_node);
      }
    }

    // 返却
    return result;
  }

  // ノード間の距離算出
  public double getDistance(Node nodeA, Node nodeB) {
    int[] posA = nodeA.getPosition();
    int[] posB = nodeB.getPosition();
    return Math.sqrt(Math.pow(posA[0] - posB[0], 2) + Math.pow(posA[1] - posB[1], 2));
  }

  // ノード全体の時間を進める
  public void forwardNowtime() {
    for(Node node : this.nodeList) {
      node.forwardNowtime();
    }
  }

}
