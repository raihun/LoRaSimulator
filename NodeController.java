import java.util.ArrayList;

public class NodeController {

  private ArrayList<Node> nodeList;
  private int cntId = 0;

  // コンストラクタ
  public NodeController() {
    this.nodeList = new ArrayList<Node>();
  }

  // ノード追加
  public void addNode(int x, int y) {
    Node node = new Node(x, y, this.cntId++);
    node.setNodeController(this);
    nodeList.add(node);
  }

  // ノード削除
  public boolean removeNode(int x, int y) {
    // ノード数チェック
    if(nodeList.size() < 1) return false;

    // 距離確認
    ArrayList<Node> removeNodeList = new ArrayList<Node>();
    for(Node node : this.nodeList) {
      int[] pos = node.getPosition();
      double distance = Math.sqrt(Math.pow(x - pos[0], 2) + Math.pow(y - pos[1], 2));
      if(distance < 5.0) removeNodeList.add(node);
    }

    // 削除リストから削除
    for(Node node : removeNodeList) nodeList.remove(nodeList.indexOf(node));
    return true;
  }

  //　ノード取得
  public ArrayList<Node> getNodes() {
    return this.nodeList;
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
