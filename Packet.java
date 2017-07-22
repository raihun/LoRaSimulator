public class Packet {

  // コンストラクタ
  public Packet() {}

  // データリンク層 操作
  private int ddst;
  private int dsrc;
  public void setDatalink(int dst, int src) {
    this.ddst = dst;
    this.dsrc = src;
  }
  public int[] getDataLink() {
    int[] datalink = {this.ddst, this.dsrc};
    return datalink;
  }

  // ネットワーク層 操作
  private int ndst;
  private int nsrc;
  public void setNetwork(int dst, int src) {
    this.ndst = dst;
    this.nsrc = src;
  }
  public int[] getNetwork() {
    int[] network = {this.ndst, this.nsrc};
    return network;
  }

  // パケットタイプ 操作
  private byte type;
  public void setType(byte type) {
    this.type = type;
  }
  public byte getType() {
    return this.type;
  }

  // ペイロード操作
  private byte[] payload = new byte[50];


}
