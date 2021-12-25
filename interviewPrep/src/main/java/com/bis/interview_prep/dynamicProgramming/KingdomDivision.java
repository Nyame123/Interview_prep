package com.bis.interview_prep.dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * King Arthur has a large kingdom that can be represented as a tree,
 * where nodes correspond to cities and edges correspond to the roads between cities.
 * The kingdom has a total of n cities numbered from 1 to n.
 *
 * The King wants to divide his kingdom between his two children, Reggie and Betty,
 * by giving each of them 0 or more cities; however, they don't get along so he must
 * divide the kingdom in such a way that they will not invade each other's cities.
 * The first sibling will invade the second sibling's city if the second sibling has
 * no other cities directly connected to it. For example, consider the kingdom configurations below
 **/
public class KingdomDivision {

    static final int MOD = 10^7+9;
    static int[][][] memo = new int[100000+10][2][2];
    static int[][][] done = new int[100000+10][2][2];
    static int cc = 1;

    public static void main(String[] args) {

        List<List<Integer>> roads = new ArrayList<>();


        addRoad(roads,1,2);
        addRoad(roads,1,5);
        addRoad(roads,2,3);
        addRoad(roads,2,4);
        addRoad(roads,5,6);
        addRoad(roads,7,5);

        int n = roads.size();


        int kingdomDivisionWays = kingdomDivision(n,roads);
        System.out.println(kingdomDivisionWays);

    }

    static void addRoad(List<List<Integer>> roads,int u,int v){
        List<Integer> road = new ArrayList<>();
        road.add(u);
        road.add(v);
        roads.add(road);
    }

    static HashMap<Integer,List<Integer>> buildGraph(List<List<Integer>> road){
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        int n = road.size();
        for(int i = 0; i < n; i++){
            int u = road.get(i).get(0);
            int v = road.get(i).get(1);
            map.putIfAbsent(u,new ArrayList<>());
            map.putIfAbsent(v,new ArrayList<>());
            map.get(u).add(v);
            map.get(v).add(u);
        }

        return map;
    }

    static int kingdomDivision(int n, List<List<Integer>> roads){
        HashMap<Integer,List<Integer>> graph = buildGraph(roads);
        for(int i = 0; i < memo.length; i++){
            memo[i][1][1] = memo[i][1][0] = memo[i][0][1] = memo[i][0][0] = 0;
            done[i][1][1] = done[i][1][0] = done[i][0][1] = done[i][0][0] = 0;
        }

        return grabSubtreeFromNode(graph,1,0,0,0)
                + grabSubtreeFromNode(graph,1,0,1,0);
    }

    static int grabSubtreeFromNode(HashMap<Integer,List<Integer>> graph,int node,int parent,int color,int ally){
        int result = 0;
        return result + getValidSubtreeFromNode(graph, node, parent, 0, color, ally);
    }

    static int getValidSubtreeFromNode(HashMap<Integer,List<Integer>> graph,int node, int parent,int index,int color, int ally){
        int result = 0;
        //base case
        if(index == graph.get(node).size()){
            return ally;
        }

        int child = graph.get(node).get(index);

        ///Ignore back edge to parent of s. Rooted tree is not suppose to have this back edge anyway.
        if (child == parent){
            return getValidSubtreeFromNode(graph,node,parent,index+1,color,ally);
        }

        //Load the value from the cache
        if (done[child][color][ally] == cc){
            return memo[child][color][ally];
        }

        done[child][color][ally] = cc;

        //For each child, we can make it ally with the parent
        //Start independent sub-tree grabSubtreeFromNode
        result = grabSubtreeFromNode(graph,child,node,color,1) *
                getValidSubtreeFromNode(graph,node,parent,index + 1,color,1);
        result %= MOD;

        //Or we can make it different too
        int temp = grabSubtreeFromNode(graph,child,node,1-color,0) *
                getValidSubtreeFromNode(graph,node,parent,index+1 ,color,ally);

        result += temp%MOD;

        memo[child][color][ally] = result;

        return result;

    }
}
class Result {

    static final int MOD = 1000000007;
    /*
     * Complete the 'kingdomDivision' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY roads
     */

    public static int kingdomDivision(int n, List<List<Integer>> roads){
        HashMap<Integer,List<Integer>> graph = buildGraph(roads);
        int[][][] memo = new int[100000+10][2][2];
        for(int i = 0; i < memo.length; i++){
            memo[i][1][1] = memo[i][1][0] = memo[i][0][1] = memo[i][0][0] = -1;
        }

        int result = grabSubtreeFromNode(graph,1,0,0,0,memo);
        result += grabSubtreeFromNode(graph,1,0,1,0,memo);

        return result;
    }


    static HashMap<Integer,List<Integer>> buildGraph(List<List<Integer>> road){
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        int n = road.size();
        for(int i = 0; i < n; i++){
            int u = road.get(i).get(0);
            int v = road.get(i).get(1);
            map.putIfAbsent(u,new ArrayList<>());
            map.putIfAbsent(v,new ArrayList<>());
            map.get(u).add(v);
            map.get(v).add(u);
        }

        return map;
    }

    static int grabSubtreeFromNode(HashMap<Integer,List<Integer>> graph,int node,int parent,int color,int ally,int[][][] memo){
        int result = 0;
        result += getValidSubtreeFromNode(graph, node, parent, 0, color, ally, memo);
        //System.out.printf("result %d",result);
        return result;
    }

    static int getValidSubtreeFromNode(HashMap<Integer,List<Integer>> graph,int node, int parent,int index,int color, int ally,int[][][] memo){
        int result = 0;
        //base case
        if(index == graph.get(node).size()){
            return ally;
        }

        int child = graph.get(node).get(index);

        ///Ignore back edge to parent of s. Rooted tree is not suppose to have this back edge anyway.
        if (child == parent){
            return getValidSubtreeFromNode(graph,node,parent,index+1,color,ally,memo);
        }

        //cout <<"Source "<<s<<" Parent "<<p<< " Child "<<t<<" Color "<< cur<<" ally "<<ally<< endl;

        //Load the value from the cache
        if (memo[child][color][ally] != -1){
            //System.out.printf("Source %d Parent %d Child %d Color %d ally %d result %d\n",node,parent,child,color,ally,memo[child][color][ally]);
            return memo[child][color][ally];
        }

        //For each child, we can make it ally with the parent
        //Start independent sub-tree grabSubtreeFromNode
        //make the child ally of the source node
        int r1 = grabSubtreeFromNode(graph,child,node,color,1,memo);
        int r2 = getValidSubtreeFromNode(graph,node,parent,index + 1,color,1,memo);
        result = (r1*r2)%MOD;

        //Or we can make it different too
        //make the child the enemy of the source node
        int temp1 = grabSubtreeFromNode(graph,child,node,1-color,0,memo);
        int temp2 = getValidSubtreeFromNode(graph,node,parent,index+1 ,color,ally,memo);

        int temp = (temp1*temp2)%MOD;
        System.out.printf("r1 %d r2 %d t1 %d t2 %d temp %d result %d\n",r1,r2,temp1,temp2,temp,MOD);
        result += temp;

        result = norm(result);

        //System.out.printf("Source %d Parent %d Child %d Color %d ally %d result %d\n",node,parent,child,color,ally,result);
        memo[child][color][ally] = result;

        return result;

    }

    static int norm(int res){
        if(res >= MOD){
            res-=MOD;
            return res;
        }else{
            return res;
        }
    }

}

 class Solution1 {
    public static void main(String[] args) throws IOException {
        File file = new File(System.getProperty("user.home"),"/Desktop/kingdom_test.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> roads = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                roads.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.kingdomDivision(n, roads);

        System.out.println(result);
        //bufferedWriter.newLine();

        bufferedReader.close();
        //bufferedWriter.close();
    }
}
