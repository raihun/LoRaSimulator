import java.util.ArrayList;
import java.util.HashMap;

public class Packet implements Cloneable {

  // コンストラクタ
  public Packet() {}

  // クローン
  @Override
  public Packet clone() {
    Packet packet = new Packet();
    try {
      packet = (Packet)super.clone();
    } catch(Exception e) {
      e.printStackTrace();
    }

    return packet;
  }

  // データリンク層 操作
  private int ddst = -1;
  private int dsrc = -1;
  public void setDatalink(int dst, int src) {
    this.ddst = dst;
    this.dsrc = src;
  }
  public int[] getDatalink() {
    int[] datalink = {this.ddst, this.dsrc};
    return datalink;
  }

  // ネットワーク層 操作
  private int ndst = -1;
  private int nsrc = -1;
  public void setNetwork(int dst, int src) {
    this.ndst = dst;
    this.nsrc = src;
  }
  public int[] getNetwork() {
    int[] network = {this.ndst, this.nsrc};
    return network;
  }

  // パケットタイプ 操作
  private byte type = 0x00;
  public void setType(byte type) {
    this.type = type;
  }
  public byte getType() {
    return this.type;
  }

  // ペイロード操作
  private byte[] payload = new byte[50];

  // ペイロード(ルーティングテーブル) 操作
  private HashMap<Integer, ArrayList<Integer>> routeList = null;
  public void setRouteList(HashMap<Integer, ArrayList<Integer>> routeList) {
    this.routeList = routeList;
  }
  public HashMap<Integer, ArrayList<Integer>> getRouteList() {
    return this.routeList;
  }

}
