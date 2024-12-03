public class Main
{
    public static void main(String[] args)
    {
        List list = new List();
        list.insert("IFBA");
        list.insert("ADS");
        list.insert("JOAO");
        list.insert("MARIA");
        list.insert("ALICE", 0);
        list.remove(4);
        list.remove(list.indexOf("JOAO"));
        list.display();
    }
}
