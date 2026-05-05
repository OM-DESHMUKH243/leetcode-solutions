package Graphs;

import java.util.*;

public class AccountsMergeDSU {

    static class DSU {

        int[] parent;

        public DSU(int n){
            parent = new int[n];
            for(int i = 0; i < n; i++){
                parent[i] = i;
            }
        }

        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            parent[find(x)] = find(y);
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts){

        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        int index = 0;

        for(List<String> acc : accounts){

            String name = acc.get(0);

            for(int i = 1; i < acc.size(); i++){

                String email = acc.get(i);

                if(!emailToIndex.containsKey(email)){
                    emailToIndex.put(email, index++);
                    emailToName.put(email, name);
                }
            }
        }

        DSU dsu = new DSU(index);

        for(List<String> acc : accounts){

            String firstEmail = acc.get(1);

            for(int i = 2; i < acc.size(); i++){

                dsu.union(
                    emailToIndex.get(firstEmail),
                    emailToIndex.get(acc.get(i))
                );
            }
        }

        Map<Integer, List<String>> groups = new HashMap<>();

        for(String email : emailToIndex.keySet()){

            int parent = dsu.find(emailToIndex.get(email));

            groups.putIfAbsent(parent, new ArrayList<>());
            groups.get(parent).add(email);
        }

        List<List<String>> result = new ArrayList<>();

        for(List<String> emails : groups.values()){

            Collections.sort(emails);

            String name = emailToName.get(emails.get(0));

            List<String> merged = new ArrayList<>();
            merged.add(name);
            merged.addAll(emails);

            result.add(merged);
        }

        return result;
    }

    public static void main(String[] args){

        List<List<String>> accounts = new ArrayList<>();

        accounts.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(Arrays.asList("John","johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary","mary@mail.com"));

        List<List<String>> result = accountsMerge(accounts);

        System.out.println(result);
    }
}
