import java.util.ArrayList;
import java.util.HashMap;

public class RouteController {

  private HashMap<Integer, ArrayList<Integer>> routeList = null;

  // コンストラクタ
  public RouteController() {
    routeList = new HashMap<Integer, ArrayList<Integer>>();
  }

  public void addRoute(int ndst, int next, int hop) {
    ArrayList<Integer> data = this.routeList.get(ndst);
    if(data == null) {
      data = new ArrayList<Integer>();
      data.add(0, next);
      data.add(1, hop);
    } else {
      int _next = data.get(0);
      int _hop = data.get(1);
      if(hop <= _hop) {
        data.set(0, next);
        data.set(1, hop);
      }
    }
    this.routeList.put(ndst, data);
  }

  public int getShortestRoute(int ndst) {
    ArrayList<Integer> data = this.routeList.get(ndst);
    if(data == null) {
      return -1;
    }
    return data.get(0);
  }

  public HashMap<Integer, ArrayList<Integer>> getRouteList() {
    return this.routeList;
  }

  public void parseRouteList(int dsrc, HashMap<Integer, ArrayList<Integer>> list) {
    if(list == null) {
      return;
    }

    for(int ndst : list.keySet()) {
      ArrayList<Integer> data = list.get(ndst);
      int hop = data.get(1);
      this.addRoute(ndst, dsrc, hop + 1);
    }
    return;
  }

}
