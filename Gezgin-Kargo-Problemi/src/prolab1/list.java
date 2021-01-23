package prolab1;

import java.util.LinkedList;
    
class list {
    int cityCount;
    LinkedList<komsu> [] cities;

    list(int cityCount) {
        this.cityCount = cityCount;
        cities = new LinkedList[cityCount];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i <cityCount ; i++) {
            cities[i] = new LinkedList<>();
        }
    }
    
    public  void addKomsu(int plaka, int komsuPlaka, int maliyet, String name) {
        komsu tmp_komsu = new komsu(plaka, komsuPlaka, maliyet,name);
        cities[plaka].add(tmp_komsu);
    }
    
    public LinkedList<komsu> getCity(int source) {
        return this.cities[source];
    }
}

class komsu {
    int plaka;
    int komsuPlaka;
    int maliyet;
    String name;

    public komsu(int plaka, int komsuPlaka, int maliyet,String name) {
        this.plaka = plaka;
        this.komsuPlaka = komsuPlaka;
        this.maliyet = maliyet;
        this.name = name;
    }

}
