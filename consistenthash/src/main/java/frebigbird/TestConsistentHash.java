package frebigbird;

import java.util.ArrayList;

/**
 * Created by jhkim on 2017-10-24.
 */
public class TestConsistentHash {
    public static void main(String[] args) {
        HashFunction hashFunction = new HashFunction();

        ArrayList<String> servers = new ArrayList<String>();
        servers.add("server1");
        servers.add("server2");
        servers.add("server3");

        ConsistentHash<String> hash = new ConsistentHash<String>(hashFunction, 10000, servers);
        System.out.println("*** Hash initialized");
        System.out.println("=============================================");

        String[] testList = {
                "aldfsdlfkjsdlfjsdfjs1",
                "boiej;klasdfskdf;sad2",
                "cs.mfjsldfkjsdfjsdlk3",
                "dldfsdlfkjsdlfjsdfjs4",
                "eldfsdlfkjsdlfjsdfjs5",
                "sfiswufpisdfasdikfjj6",
                "sldfsdlfkjsdlfjsdfjs7",
                "sldfsdlfkjsdlfjsdfjs8",
                "zskjfsdkfjsdlkfjsdlk9"
        };

        for (String test: testList) {
            System.out.println(">> get " + test + " = " + hash.get(test));
        }

        System.out.println("=============================================");

        hash.remove("server2");
        System.out.println("*** server2 removed");

        System.out.println("=============================================");

        for (String test: testList) {
            System.out.println(">> get " + test + " = " + hash.get(test));
        }

        System.out.println("=============================================");
    }
}
