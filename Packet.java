public class Packet {

  private int ddst;
  private int dsrc;
  private int ndst;
  private int nsrc;
  private byte[] payload = new byte[50];

  // コンストラクタ
  public Packet() {}

  // データリンク層 操作
  public void setDatalink(int dst, int src) {
    this.ddst = dst;
    this.dsrc = src;
  }
  public int[] getDataLink() {
    int[] datalink = {this.ddst, this.dsrc};
    return datalink;
  }

  // ネットワーク層 操作
  public void setNetwork(int dst, int src) {
    this.ndst = dst;
    this.nsrc = src;
  }
  public int[] getNetwork() {
    int[] network = {this.ndst, this.nsrc};
    return network;
  }


}
